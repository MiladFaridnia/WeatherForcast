package com.faridnia.weatherforcast.viewmodel

import com.faridnia.weatherforcast.model.forcastresponse.City
import com.faridnia.weatherforcast.model.forcastresponse.ForecastResult
import com.faridnia.weatherforcast.model.onecallresponse.WeatherInfo
import com.faridnia.weatherforcast.network.RequestCompleteListener


interface ForecastResultModel {

    fun getCityListFromAssets(callback: RequestCompleteListener<MutableList<City>>)

    fun getCityListFromWeb(callback: RequestCompleteListener<MutableList<City>>)

    fun getWeatherForecast(cityId: Int, callback: RequestCompleteListener<ForecastResult>)

    fun getWeatherInfo(
        latitude: Double,
        longitude: Double,
        callback: RequestCompleteListener<WeatherInfo>
    )

}