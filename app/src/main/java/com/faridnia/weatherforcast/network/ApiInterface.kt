package com.faridnia.weatherforcast.network

import com.faridnia.weatherforcast.model.forcastresponse.City
import com.faridnia.weatherforcast.model.forcastresponse.ForecastResult
import com.faridnia.weatherforcast.model.onecallresponse.WeatherInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("forecast")
    fun getWeatherForecast(@Query("id") cityId: Int): Call<ForecastResult>

    @GET("not_found_yet")
    fun getCities(@Query("countryCode") countryCode: String): Call<List<City>>

    @GET("onecall")
    fun getWeatherInfoOneCall(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("exclude") minutely: String = "minutely"
    ): Call<WeatherInfo>

}