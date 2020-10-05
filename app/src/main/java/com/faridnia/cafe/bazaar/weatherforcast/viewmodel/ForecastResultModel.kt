package com.faridnia.cafe.bazaar.weatherforcast.viewmodel

import com.faridnia.cafe.bazaar.weatherforcast.model.City
import com.faridnia.cafe.bazaar.weatherforcast.model.ForecastResult
import com.faridnia.cafe.bazaar.weatherforcast.network.RequestCompleteListener


interface ForecastResultModel {

    fun getCityListFromAssets(callback: RequestCompleteListener<MutableList<City>>)

    fun getCityListFromWeb(callback: RequestCompleteListener<MutableList<City>>)

    fun getWeatherForecast(cityId: Int, callback: RequestCompleteListener<ForecastResult>)

}