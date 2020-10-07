package com.faridnia.cafe.bazaar.weatherforcast.network

import com.faridnia.cafe.bazaar.weatherforcast.model.City
import com.faridnia.cafe.bazaar.weatherforcast.model.ForecastResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("forecast")
    fun getWeatherForecast(@Query("id") cityId: Int): Call<ForecastResult>

    @GET("not_found_yet")
    fun getCities(@Query("countryCode") countryCode: String): Call<List<City>>

}