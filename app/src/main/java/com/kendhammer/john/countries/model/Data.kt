package com.kendhammer.john.countries.model

import com.google.gson.annotations.SerializedName


data class Country(
    @SerializedName("name")
    var countryName: String?,
    @SerializedName("capital")
    var capital: String?,
    @SerializedName("flagPNG")
    var flag: String?
)
