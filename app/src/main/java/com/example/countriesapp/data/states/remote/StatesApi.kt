package com.example.countriesapp.data.states.remote

import com.example.countriesapp.data.states.model.StateResponse
import retrofit2.Response
import retrofit2.http.GET

interface StatesApi {
    @GET("countries/states")
    suspend fun getStates(): Response<StateResponse>
}