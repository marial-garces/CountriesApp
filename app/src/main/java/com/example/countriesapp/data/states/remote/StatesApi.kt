package com.example.countriesapp.data.states.remote

import com.example.countriesapp.data.states.model.CitiesResponse
import com.example.countriesapp.data.states.model.CityRequest
import com.example.countriesapp.data.states.model.CountryRequest
import com.example.countriesapp.data.states.model.PopulationRequest
import com.example.countriesapp.data.states.model.PopulationResponse
import com.example.countriesapp.data.states.model.StateResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface StatesApi {
    @POST("countries/states")
    suspend fun getStates(@Body request: CountryRequest): Response<StateResponse>

    @POST("countries/state/cities")
    suspend fun getCities(@Body request: CityRequest): Response<CitiesResponse>

    @POST("countries/population/cities")
    suspend fun getPopulation(@Body request: PopulationRequest): Response<PopulationResponse>
}