package com.faridnia.cafe.bazaar.weatherforcast.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class ForecastResultViewModelFactory @Inject constructor(private val forecastResult: ForecastResultModel) :

    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(ForecastResultModel::class.java)
            .newInstance(forecastResult)
    }
}