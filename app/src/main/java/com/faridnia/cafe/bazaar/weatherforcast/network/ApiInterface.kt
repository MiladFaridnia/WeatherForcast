package com.faridnia.cafe.bazaar.weatherforcast.network

import com.faridnia.cafe.bazaar.weatherforcast.model.ForecastResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("forecast")
    fun getWeatherInfo(@Query("id") cityId: Int): Call<ForecastResult>

}