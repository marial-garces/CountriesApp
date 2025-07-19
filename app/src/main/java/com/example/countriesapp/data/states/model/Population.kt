package com.example.countriesapp.data.states.model

import com.google.gson.annotations.SerializedName

data class PopulationRequest(
    val city: String
)

data class PopulationResponse(
    val error: Boolean = false,
    val msg: String? = null,
    val data: List<CityPopulation> = emptyList()
)

data class CityPopulation(
    val city: String,
    @SerializedName("populationCounts") val populationCounts: List<PopulationCount> = emptyList()
)

data class PopulationCount(
    val year: String,
    val value: String
)