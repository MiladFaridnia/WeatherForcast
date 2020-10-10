package com.faridnia.weatherforcast.viewmodel

import com.faridnia.weatherforcast.model.City
import com.faridnia.weatherforcast.model.ForecastResult
import com.faridnia.weatherforcast.network.RequestCompleteListener


interface ForecastResultModel {

    fun getCityListFromAssets(callback: RequestCompleteListener<MutableList<City>>)

    fun getCityListFromWeb(callback: RequestCompleteListener<MutableList<City>>)

    fun getWeatherForecast(cityId: Int, callback: RequestCompleteListener<ForecastResult>)

}