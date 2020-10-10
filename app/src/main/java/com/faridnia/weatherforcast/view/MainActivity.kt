package com.faridnia.weatherforcast.view

import dagger.android.support.DaggerAppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.faridnia.weatherforcast.R
import com.faridnia.weatherforcast.viewmodel.ForecastResultViewModel
import com.faridnia.weatherforcast.viewmodel.ForecastResultViewModelFactory
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var factory: ForecastResultViewModelFactory

    private lateinit var viewModel: ForecastResultViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this, factory).get(ForecastResultViewModel::class.java)

        observeCityData()

        observeWeatherData()

    }

    private fun observeCityData() {
        viewModel.cityListLiveData.observe(this, Observer {
            Log.d("Milad" , "Cities: $it")
        })
    }

    private fun observeWeatherData() {
        viewModel.forecastResultLiveData.observe(this, Observer { result->
            Log.d("Milad", "result : " + result.city)
        })
    }
}
