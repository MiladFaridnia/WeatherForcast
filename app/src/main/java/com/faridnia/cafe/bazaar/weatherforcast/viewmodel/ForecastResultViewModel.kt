package com.faridnia.cafe.bazaar.weatherforcast.viewmodel

import com.faridnia.cafe.bazaar.weatherforcast.model.City
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.faridnia.cafe.bazaar.weatherforcast.model.ForecastResult
import com.faridnia.cafe.bazaar.weatherforcast.network.RequestCompleteListener
import javax.inject.Inject

class ForecastResultViewModel @Inject constructor(var model: ForecastResultModel) : ViewModel() {

    val cityListLiveData = MutableLiveData<MutableList<City>>()
    val cityListFailureLiveData = MutableLiveData<String>()
    val weatherInfoLiveData = MutableLiveData<ForecastResult>()
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

                progressBarLiveData.postValue(false)

                weatherInfoLiveData.postValue(data)
            }

            override fun onRequestFailed(errorMessage: String) {

                progressBarLiveData.postValue(false)

                weatherInfoFailureLiveData.postValue(errorMessage)
            }
        })
    }
}