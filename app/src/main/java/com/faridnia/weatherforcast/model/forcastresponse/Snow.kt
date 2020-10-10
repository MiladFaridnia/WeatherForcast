package com.faridnia.weatherforcast.model.forcastresponse

import com.google.gson.annotations.SerializedName

data class Snow(
    @SerializedName("3h")
    val h: Double
)