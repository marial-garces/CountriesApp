package com.example.countriesapp.data.countries.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializer

data class CountriesResponse(
    @SerializedName("data")
    val countries: List<Country>? = null,
    val error: Boolean = false,
    val msg: String? = null
)

data class Country(
    val name: String,
    val flag: String,
    val iso2: String,
    val iso3: String
)

