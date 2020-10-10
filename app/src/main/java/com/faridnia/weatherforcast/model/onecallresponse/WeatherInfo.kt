package com.faridnia.weatherforcast.model.onecallresponse

import com.google.gson.annotations.SerializedName

data class WeatherInfo(
    val current: Current,
    val daily: List<Daily>,
    val hourly: List<Hourly>,
    val lat: Double,
    val lon: Double,
    val timezone: String,
    @SerializedName("timezone_offset")
    val timezoneOffset: Int
)