package com.example.countriesapp.data.countries.repository

import com.example.countriesapp.data.countries.model.Country
import com.example.countriesapp.data.countries.remote.CountriesApi
import javax.inject.Inject

class CountriesRepository @Inject constructor(
    private val api: CountriesApi
) {
    suspend fun getCountries(): Result<List<Country>> {
        return try {
            val response = api.getCountries()
            if (response.isSuccessful && response.body() != null) {
                val countriesResponse = response.body()!!
                if (!countriesResponse.error) {
                    Result.success(countriesResponse.countries)
                } else {
                    Result.failure(Exception("API Error: ${countriesResponse.msg}"))
                }
            } else {
                Result.failure(Exception("Error to fetch countries: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}