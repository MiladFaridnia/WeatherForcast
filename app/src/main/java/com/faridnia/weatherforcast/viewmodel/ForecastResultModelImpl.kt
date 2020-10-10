package com.faridnia.weatherforcast.viewmodel

import android.content.Context
import com.faridnia.weatherforcast.model.forcastresponse.City
import com.faridnia.weatherforcast.model.forcastresponse.ForecastResult
import com.faridnia.weatherforcast.model.onecallresponse.WeatherInfo
import com.faridnia.weatherforcast.network.ApiInterface
import com.faridnia.weatherforcast.network.RequestCompleteListener
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class ForecastResultModelImpl @Inject constructor(
    private val context: Context,
    private val apiInterface: ApiInterface
) : ForecastResultModel {

    override fun getCityListFromAssets(callback: RequestCompleteListener<MutableList<City>>) {
        try {
            val stream = context.assets.open("city_list.json")

            val size = stream.available()
            val buffer = ByteArray(size)
            stream.read(buffer)
            stream.close()
            val tContents = String(buffer)

            val groupListType = object : TypeToken<ArrayList<City>>() {}.type
            val gson = GsonBuilder().create()
            val cityList: MutableList<City> = gson.fromJson(tContents, groupListType)

            callback.onRequestSuccess(cityList)

        } catch (e: IOException) {
            e.printStackTrace()
            callback.onRequestFailed(requireNotNull(e.localizedMessage))
        }
    }

    override fun getCityListFromWeb(callback: RequestCompleteListener<MutableList<City>>) {

        val call: Call<List<City>> = apiInterface.getCities("IR")

        call.enqueue(object : Callback<List<City>> {

            override fun onResponse(
                call: Call<List<City>>,
                response: Response<List<City>>
            ) {
                if (response.body() != null)
                    callback.onRequestSuccess(requireNotNull(response.body()).toMutableList())
                else
                    callback.onRequestFailed(response.message())
            }

            override fun onFailure(call: Call<List<City>>, t: Throwable) {
                callback.onRequestFailed(requireNotNull(t.localizedMessage))
            }
        })
    }

    override fun getWeatherForecast(
        cityId: Int,
        callback: RequestCompleteListener<ForecastResult>
    ) {

        val call: Call<ForecastResult> = apiInterface.getWeatherForecast(cityId)

        call.enqueue(object : Callback<ForecastResult> {

            override fun onResponse(
                call: Call<ForecastResult>,
                response: Response<ForecastResult>
            ) {
                if (response.body() != null)
                    callback.onRequestSuccess(requireNotNull(response.body()))
                else
                    callback.onRequestFailed(response.message())
            }

            override fun onFailure(call: Call<ForecastResult>, t: Throwable) {
                callback.onRequestFailed(requireNotNull(t.localizedMessage))
            }
        })
    }

    override fun getWeatherInfo(
        latitude: Double,
        longitude: Double,
        callback: RequestCompleteListener<WeatherInfo>
    ) {

        val call: Call<WeatherInfo> =
            apiInterface.getWeatherInfoOneCall(lat = latitude, lon = longitude)

        call.enqueue(object : Callback<WeatherInfo> {

            override fun onResponse(
                call: Call<WeatherInfo>,
                response: Response<WeatherInfo>
            ) {
                if (response.body() != null)
                    callback.onRequestSuccess(requireNotNull(response.body()))
                else
                    callback.onRequestFailed(response.message())
            }

            override fun onFailure(call: Call<WeatherInfo>, t: Throwable) {
                callback.onRequestFailed(requireNotNull(t.localizedMessage))
            }
        })
    }


}