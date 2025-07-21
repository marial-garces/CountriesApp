package com.example.countriesapp.data.states.repository

import com.example.countriesapp.data.states.model.CityRequest
import com.example.countriesapp.data.states.model.CountryRequest
import com.example.countriesapp.data.states.model.PopulationCounts
import com.example.countriesapp.data.states.model.PopulationRequest
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
                    Result.failure(Exception("API Error: ${body.msg}"))
                }
            }else {
                Result.failure(Exception("Error fetching states: ${response.message()}"))
            }
        }catch (e: Exception){
            Result.failure(e)
        }
    }

    suspend fun getCities(country: String, state: String): Result<List<String>> {
        return try {
            val response = api.getCities(CityRequest(country, state))
            if (response.isSuccessful && response.body() != null) {
                val body = response.body()!!
                if (!body.error) {
                    Result.success(body.data)
                } else {
                    Result.failure(Exception("API Error: ${body.msg}"))
                }
            } else {
                Result.failure(Exception("Error fetching cities: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getPopulation(city: String): Result<List<PopulationCounts>> {
        return try {
            println("Fetching population for city: $city") // Debug log
            val response = api.getPopulation(PopulationRequest(city))
            println("Response code: ${response.code()}") // Debug log

            if (response.isSuccessful && response.body() != null) {
                val body = response.body()!!
                println("Response body error: ${body.error}, msg: ${body.msg}") // Debug log
                if (body.error == true) {
                    val counts = body.data?.populationCounts ?: emptyList()
                    println("Population counts found: ${counts.size}") // Debug log
                    Result.success(counts)
                } else {
                    Result.failure(Exception("API Error: ${body.msg}"))
                }
            } else {
                Result.failure(Exception("Error fetching population: ${response.message()}"))
            }
        } catch (e: Exception) {
            println("Exception in getPopulation: ${e.message}") // Debug log
            Result.failure(e)
        }
    }
}