package com.example.countriesapp.data.states.model

import com.google.gson.annotations.SerializedName


data class StateResponse(
    @SerializedName("data")
    val data : StateData? = null,
    val error: Boolean = false,
    val msg: String? = null
)

data class StateData(
    val name: String,
    val iso3: String,
    val states: List<States> = emptyList()
)

data class States(
    val name: String
)

data class CountryRequest(
    val country: String
)

data class CityRequest(
    val country: String,
    val state: String
)

data class CitiesResponse(
    val error: Boolean = false,
    val msg: String? = null,
    val data: List<String> = emptyList()
)