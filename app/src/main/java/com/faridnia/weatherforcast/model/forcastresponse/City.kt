package com.faridnia.weatherforcast.model.forcastresponse

data class City(
    val coord: Coord,
    val country: String,
    val id: Int,
    val name: String
)