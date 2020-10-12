package com.faridnia.weatherforcast.view

import dagger.android.support.DaggerAppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.faridnia.weatherforcast.R
import com.faridnia.weatherforcast.model.WeatherConditions
import com.faridnia.weatherforcast.viewmodel.ForecastResultViewModel
import com.faridnia.weatherforcast.viewmodel.ForecastResultViewModelFactory
import kotlinx.android.synthetic.main.city_temp_info_layout.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    private val selectedCityName: String = "Tehran"

    @Inject
    lateinit var factory: ForecastResultViewModelFactory

    private lateinit var viewModel: ForecastResultViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this, factory).get(ForecastResultViewModel::class.java)

        observeCityData()

        observeWeatherData()

        viewModel.getWeatherInfo(47.159401, 34.330502)//TODO Sample Data

    }

    private fun observeCityData() {
        viewModel.cityListLiveData.observe(this, Observer {
            Log.d("Milad", "Cities: $it")
        })
    }

    private fun observeWeatherData() {
        viewModel.weatherInfoLiveData.observe(this, Observer { weatherInfo ->
            Log.d("Milad", "result : " + weatherInfo.current)

            val cityTemperature = weatherInfo.current.temp

            val cityMinMaxTemperature = weatherInfo.current.dewPoint

            val cityName = selectedCityName

                val cityWeather = weatherInfo.current.weather[0].main
            showTemperatureInfo(cityTemperature, cityMinMaxTemperature, cityName, cityWeather)

        })
    }

    private fun showTemperatureInfo(
        cityTemperature: Double,
        cityMinMaxTemperature: Double,
        cityName: String,
        cityWeather: String
    ) {
        cityNameTextView.text = cityName
        cityTemperatureTextView.text = cityTemperature.toString()
        cityMinMaxTempTextView.text = cityMinMaxTemperature.toString()
        when (cityWeather) {
            WeatherConditions.Clear.name -> {
                cityWeatherImageView.setImageResource(R.drawable.ic_0_clear_sky_morning)
            }
            WeatherConditions.BrokenClouds.name -> {
                cityWeatherImageView.setImageResource(R.drawable.ic_0_broken_cloud)
            }
            WeatherConditions.FewClouds.name -> {
                cityWeatherImageView.setImageResource(R.drawable.ic_0_few_cloud_dawn)
            }
            WeatherConditions.Mist.name -> {
                cityWeatherImageView.setImageResource(R.drawable.ic_0_mist)
            }
            WeatherConditions.Rain.name -> {
                cityWeatherImageView.setImageResource(R.drawable.ic_0_rain)
            }
            WeatherConditions.ScatteredClouds.name -> {
                cityWeatherImageView.setImageResource(R.drawable.ic_0_scattered_clouds)
            }
            WeatherConditions.ShowerRain.name -> {
                cityWeatherImageView.setImageResource(R.drawable.ic_0_shower_raint)
            }
            WeatherConditions.Thunderstorm.name -> {
                cityWeatherImageView.setImageResource(R.drawable.ic_0__thunderstorm)
            }
            WeatherConditions.Snow.name -> {
                cityWeatherImageView.setImageResource(R.drawable.ic_0_snow)
            }
            else -> {
                cityWeatherImageView.setImageResource(R.drawable.ic_0_clear_sky_morning)
            }
        }
    }
}
