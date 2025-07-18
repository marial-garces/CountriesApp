package com.example.countriesapp.data.states.repository

import com.example.countriesapp.data.countries.model.Country
import com.example.countriesapp.data.countries.remote.CountriesApi
import javax.inject.Inject

class StatesRepository @Inject constructor(
    private val api: CountriesApi
) {

}