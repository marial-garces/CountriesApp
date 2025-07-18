package com.example.countriesapp.data.states.repository

import com.example.countriesapp.data.states.model.CountryRequest
import com.example.countriesapp.data.states.model.States
import com.example.countriesapp.data.states.remote.StatesApi
import javax.inject.Inject

class StatesRepository @Inject constructor(
    private val api: StatesApi
) {
    suspend fun getStates(country: String): Result<List<States>> {
        return try {
            val response = api.getStates(CountryRequest(country))
            if (response.isSuccessful && response.body() != null){
                val body = response.body()!!
                if (!body.error){
                    Result.success(body.data?.states ?: emptyList())
                } else {
                    Result.failure(Exception("API Error: ${'$'}{body.msg}"))
                }
            }else {
                Result.failure(Exception("Error fetching states: ${'$'}{response.message()}"))
            }
        }catch (e: Exception){
            Result.failure(e)
        }
    }
}