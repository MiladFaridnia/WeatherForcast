package com.faridnia.weatherforcast.viewmodel

import android.util.Log
import com.faridnia.weatherforcast.model.City
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.faridnia.weatherforcast.model.ForecastResult
import com.faridnia.weatherforcast.network.RequestCompleteListener
import javax.inject.Inject

class ForecastResultViewModel @Inject constructor(var model: ForecastResultModel) : ViewModel() {

    val cityListLiveData = MutableLiveData<MutableList<City>>()
    val cityListFailureLiveData = MutableLiveData<String>()
    val forecastResultLiveData = MutableLiveData<ForecastResult>()
    val forecastResultFailureLiveData = MutableLiveData<String>()
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
}