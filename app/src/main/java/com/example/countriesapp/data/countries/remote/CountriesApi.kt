package com.example.countriesapp.data.countries.remote

import com.example.countriesapp.data.countries.model.CountriesResponse
import retrofit2.Response
import retrofit2.http.GET

interface CountriesApi {
    @GET("countries/flag/images")
    suspend fun getCountries(): Response<CountriesResponse>
}