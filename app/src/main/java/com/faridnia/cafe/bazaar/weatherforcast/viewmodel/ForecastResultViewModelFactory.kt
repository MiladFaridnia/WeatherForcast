package com.faridnia.cafe.bazaar.weatherforcast.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.faridnia.cafe.bazaar.weatherforcast.model.ForecastResult
import javax.inject.Inject

class ForecastResultViewModelFactory @Inject constructor(private val forecastResult: ForecastResult) :

    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(ForecastResult::class.java)
            .newInstance(forecastResult)
    }
}