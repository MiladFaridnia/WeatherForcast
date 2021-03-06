package com.faridnia.weatherforcast.model.forcastresponse

data class ForecastResult(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<WeatherForecast>,
    val message: Double
)