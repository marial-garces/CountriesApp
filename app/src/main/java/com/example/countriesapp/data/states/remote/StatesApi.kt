package com.example.countriesapp.data.states.remote

import com.example.countriesapp.data.states.model.CountryRequest
import com.example.countriesapp.data.states.model.StateResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface StatesApi {
    @POST("countries/states")
    suspend fun getStates(@Body request: CountryRequest): Response<StateResponse>
}