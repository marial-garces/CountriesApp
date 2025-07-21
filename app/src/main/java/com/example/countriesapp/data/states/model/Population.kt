package com.example.countriesapp.data.states.model

import com.google.gson.annotations.SerializedName

data class PopulationRequest(
    val city: String
)

data class PopulationResponse(
    @SerializedName("error") var error : Boolean? = null,
    @SerializedName("msg") var msg   : String?  = null,
    @SerializedName("data") var data  : PopulationData?    = PopulationData()
)


data class PopulationData (
    @SerializedName("city") var city             : String?                     = null,
    @SerializedName("country") var country          : String?                     = null,
    @SerializedName("populationCounts") var populationCounts : ArrayList<PopulationCounts> = arrayListOf()
)

data class PopulationCounts (
    @SerializedName("year") var year       : String? = null,
    @SerializedName("value") var value      : String? = null,
    @SerializedName("sex") var sex        : String? = null,
    @SerializedName("reliabilty") var reliabilty : String? = null
)