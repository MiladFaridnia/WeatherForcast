package com.faridnia.weatherforcast.model.forcastresponse

import com.google.gson.annotations.SerializedName

data class WeatherForecast (
    val clouds: Clouds,
    val dt: Int,
    @SerializedName("dt_txt")
    val dtTxt: String,
    val main: Main,
    val snow: Snow,
    val sys: Sys,
    val weather: List<Weather>,
    val wind: Wind
)