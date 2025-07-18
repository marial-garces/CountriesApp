package com.example.countriesapp.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.countriesapp.data.states.model.States
import com.example.countriesapp.data.states.repository.StatesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class StatesUiState(
    val states: List<States> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class StatesViewModel @Inject constructor(
    private val repository: StatesRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel(){
    private val country: String = checkNotNull(savedStateHandle["country"])

    private val _uiState = MutableStateFlow(StatesUiState())
    val uiState: StateFlow<StatesUiState> = _uiState.asStateFlow()

    init {
        if (country.isNotEmpty()){
            loadStates(country)
        }
    }

    private fun loadStates(country: String){
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            repository.getStates(country)
                .onSuccess { states ->
                    _uiState.value = _uiState.value.copy(
                        states = states,
                        isLoading = false,
                        error = null
                    )
                }
                .onFailure { exception ->
                    _uiState.value = _uiState.value.copy(
                        states = emptyList(),
                        isLoading = false,
                        error = exception.message
                    )
                }
        }
    }

    fun retry(){
        if (country.isNotEmpty()){
            loadStates(country)
        }
    }
}