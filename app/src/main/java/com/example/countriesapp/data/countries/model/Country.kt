package com.example.countriesapp.data.countries.model

data class CountriesResponse(
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

