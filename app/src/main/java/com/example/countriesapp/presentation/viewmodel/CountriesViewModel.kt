package com.example.countriesapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countriesapp.data.countries.model.Country
import com.example.countriesapp.data.countries.repository.CountriesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CountriesUiState(
    val countries: List<Country> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class CountriesViewModel(private val repository: CountriesRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(CountriesUiState())
    val uiState: StateFlow<CountriesUiState> = _uiState.asStateFlow()

    init {
        loadCountries()
    }

    private fun loadCountries(){
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            repository.getCountries()
                .onSuccess { countries ->
                    _uiState.value = _uiState.value.copy(
                        countries = countries,
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
        loadCountries()
    }
}
