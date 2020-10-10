package com.faridnia.weatherforcast.viewmodel

import android.util.Log
import com.faridnia.weatherforcast.model.forcastresponse.City
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.faridnia.weatherforcast.model.onecallresponse.WeatherInfo
import com.faridnia.weatherforcast.model.forcastresponse.ForecastResult
import com.faridnia.weatherforcast.network.RequestCompleteListener
import javax.inject.Inject

class ForecastResultViewModel @Inject constructor(var model: ForecastResultModel) : ViewModel() {

    val cityListLiveData = MutableLiveData<MutableList<City>>()
    val cityListFailureLiveData = MutableLiveData<String>()
    val forecastResultLiveData = MutableLiveData<ForecastResult>()
    val forecastResultFailureLiveData = MutableLiveData<String>()
    val weatherInfoLiveData = MutableLiveData<WeatherInfo>()
    val weatherInfoFailureLiveData = MutableLiveData<String>()
    val progressBarLiveData = MutableLiveData<Boolean>()

    fun getCityList() {
        model.getCityListFromAssets(object :
            RequestCompleteListener<MutableList<City>> {
            override fun onRequestSuccess(data: MutableList<City>) {
                cityListLiveData.postValue(data)
            }

            override fun onRequestFailed(errorMessage: String) {
                cityListFailureLiveData.postValue(errorMessage)
            }
        })
    }

    fun getForecastResult(cityId: Int) {

        progressBarLiveData.postValue(true)

        model.getWeatherForecast(cityId, object :
            RequestCompleteListener<ForecastResult> {
            override fun onRequestSuccess(data: ForecastResult) {
                Log.d("Milad", "onRequestSuccess: $data")

                progressBarLiveData.postValue(false)

                forecastResultLiveData.postValue(data)
            }

            override fun onRequestFailed(errorMessage: String) {
                Log.d("Milad", "onRequestFailed: $errorMessage")
                progressBarLiveData.postValue(false)

                forecastResultFailureLiveData.postValue(errorMessage)
            }
        })
    }

    fun getWeatherInfo(latitude: Double, longitude : Double) {

        progressBarLiveData.postValue(true)

        model.getWeatherInfo(latitude,longitude, object :
            RequestCompleteListener<WeatherInfo> {
            override fun onRequestSuccess(data: WeatherInfo) {
                Log.d("Milad", "onRequestSuccess: $data")

                progressBarLiveData.postValue(false)

                weatherInfoLiveData.postValue(data)
            }

            override fun onRequestFailed(errorMessage: String) {
                Log.d("Milad", "onRequestFailed: $errorMessage")
                progressBarLiveData.postValue(false)

                weatherInfoFailureLiveData.postValue(errorMessage)
            }
        })
    }

}