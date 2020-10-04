package com.faridnia.cafe.bazaar.weatherforcast.model

data class ForecastResult(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<WeatherForecast>,
    val message: Double
)