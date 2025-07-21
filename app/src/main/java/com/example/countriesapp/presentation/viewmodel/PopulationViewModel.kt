package com.example.countriesapp.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countriesapp.data.states.model.PopulationCount
import com.example.countriesapp.data.states.repository.StatesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class PopulationUiState(
    val counts: List<PopulationCount> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class PopulationViewModel @Inject constructor(
    private val repository: StatesRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val city: String = savedStateHandle.get<String>("city")
        ?.let { java.net.URLDecoder.decode(it, java.nio.charset.StandardCharsets.UTF_8.toString()) }
        ?: ""

    private val _uiState = MutableStateFlow(PopulationUiState())
    val uiState: StateFlow<PopulationUiState> = _uiState.asStateFlow()

    init {
        if (city.isNotEmpty()) {
            loadPopulation(city)
        }
    }

    private fun loadPopulation(city: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            repository.getPopulation(city)
                .onSuccess { counts ->
                    _uiState.value = _uiState.value.copy(
                        counts = counts,
                        isLoading = false,
                        error = null
                    )
                }
                .onFailure { exception ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = exception.message
                    )
                }
        }
    }

    fun retry() {
        if (city.isNotEmpty()) {
            loadPopulation(city)
        }
    }
}