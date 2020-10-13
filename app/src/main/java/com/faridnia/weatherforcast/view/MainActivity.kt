package com.faridnia.weatherforcast.view

import dagger.android.support.DaggerAppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.faridnia.weatherforcast.R
import com.faridnia.weatherforcast.R.drawable.*
import com.faridnia.weatherforcast.model.DayTimes
import com.faridnia.weatherforcast.model.WeatherConditions
import com.faridnia.weatherforcast.model.onecallresponse.Hourly
import com.faridnia.weatherforcast.model.onecallresponse.WeatherInfo
import com.faridnia.weatherforcast.util.Utils
import com.faridnia.weatherforcast.viewmodel.ForecastResultViewModel
import com.faridnia.weatherforcast.viewmodel.ForecastResultViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
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

            setMainBackground(weatherInfo.current.dt)

            showTemperatureInfo(
                weatherInfo.current.temp,
                getMinMaxTemperature(weatherInfo),
                selectedCityName,
                weatherInfo.current.weather[0].description,
                Utils().getDayTime(weatherInfo.current.dt)
            )

            showNextHours(weatherInfo.hourly)

        })
    }

    private fun getMinMaxTemperature(weatherInfo: WeatherInfo) =
        Utils().getTemperature(weatherInfo.daily[0].temp.min) + "/"+
                Utils().getTemperature(weatherInfo.daily[0].temp.max)

    private fun showNextHours(hours: List<Hourly>) {
        var i = 0
        for (hour in hours) {
            if (i <  7) {
                val layout = NextHourWeatherLayout(this)
                layout.setNextHourData(hour)
                layoutWeatherNextHours.addView(layout)
                i += 1
            }
        }
    }

    private fun setMainBackground(dt: Long) {
        when (Utils().getDayTime(dt)) {
            DayTimes.Dawn -> {
                mainActivityContainer.background = getDrawable(dawn_background)
            }
            DayTimes.Morning -> {
                mainActivityContainer.background = getDrawable(morning_background)
            }
            DayTimes.Noon -> {
                mainActivityContainer.background = getDrawable(noon_background)
            }
            DayTimes.Evening -> {
                mainActivityContainer.background = getDrawable(evening_background)
            }
            DayTimes.Night -> {
                mainActivityContainer.background = getDrawable(night_background)
            }
            else -> {
                mainActivityContainer.background = getDrawable(morning_background)
            }
        }
    }

    private fun showTemperatureInfo(
        cityTemperature: Double,
        cityMinMaxTemperature: String,
        cityName: String,
        cityWeather: String,
        dayTime: DayTimes
    ) {
        cityNameTextView.text = cityName
        cityTemperatureTextView.text = Utils().getTemperature(cityTemperature)
        cityMinMaxTempTextView.text = cityMinMaxTemperature.toString()
        when (cityWeather) {
            WeatherConditions.Clear.name -> {
                setClearWeatherBackground(dayTime)
            }
            WeatherConditions.BrokenClouds.name -> {
                cityWeatherImageView.setImageResource(ic_0_broken_cloud)
            }
            WeatherConditions.FewClouds.name -> {
                setFeaCloudsBackground(dayTime)
            }
            WeatherConditions.Mist.name -> {
                cityWeatherImageView.setImageResource(ic_0_mist)
            }
            WeatherConditions.Rain.name -> {
                cityWeatherImageView.setImageResource(ic_0_rain)
            }
            WeatherConditions.ScatteredClouds.name -> {
                cityWeatherImageView.setImageResource(ic_0_scattered_clouds)
            }
            WeatherConditions.ShowerRain.name -> {
                cityWeatherImageView.setImageResource(ic_0_shower_raint)
            }
            WeatherConditions.Thunderstorm.name -> {
                cityWeatherImageView.setImageResource(ic_0__thunderstorm)
            }
            WeatherConditions.Snow.name -> {
                cityWeatherImageView.setImageResource(ic_0_snow)
            }
            else -> {
                cityWeatherImageView.setImageResource(ic_0_clear_sky_morning)
            }
        }
    }

    private fun setFeaCloudsBackground(dayTime: DayTimes) {
        if (dayTime == DayTimes.Dawn) {
            cityWeatherImageView.setImageResource(ic_0_few_cloud_dawn)
        } else if (dayTime == DayTimes.Morning || dayTime == DayTimes.Noon) {
            cityWeatherImageView.setImageResource(ic_0_few_cloud_morning)
        } else if (dayTime == DayTimes.Evening || dayTime == DayTimes.Night) {
            cityWeatherImageView.setImageResource(ic_0_few_cloud_evening)
        } else {
            cityWeatherImageView.setImageResource(ic_0_clear_sky_morning)
        }
    }

    private fun setClearWeatherBackground(dayTime: DayTimes) {
        if (dayTime == DayTimes.Dawn) {
            cityWeatherImageView.setImageResource(ic_0_clear_sky_dawn)
        } else if (dayTime == DayTimes.Morning || dayTime == DayTimes.Noon) {
            cityWeatherImageView.setImageResource(ic_0_clear_sky_morning)
        } else if (dayTime == DayTimes.Evening) {
            cityWeatherImageView.setImageResource(ic_0_clear_sky_evening)
        } else if (dayTime == DayTimes.Night) {
            cityWeatherImageView.setImageResource(ic_0_clear_sky_night)
        } else {
            cityWeatherImageView.setImageResource(ic_0_clear_sky_morning)
        }
    }
}
