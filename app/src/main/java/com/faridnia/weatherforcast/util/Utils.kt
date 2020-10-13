package com.faridnia.weatherforcast.util

import com.faridnia.weatherforcast.R
import com.faridnia.weatherforcast.model.DayTimes
import com.faridnia.weatherforcast.model.WeatherConditions
import java.text.SimpleDateFormat
import java.util.*


class Utils {
    fun getDayTime(dt: Long): DayTimes {

        when (Date(dt).hours) {
            in 0..6 -> {
                return DayTimes.Dawn
            }
            in 7..11 -> {
                return DayTimes.Morning
            }
            in 12..16 -> {
                return DayTimes.Noon
            }
            in 17..19 -> {
                return DayTimes.Evening
            }
            in 20..23 -> {
                return DayTimes.Night
            }
            else -> return DayTimes.None
        }
    }

    fun getTemperature(temp: Double): String {
        return (temp - 273).toInt().toString()
    }

    fun getHourFromDate(s: Long): String? {
        return try {
            val sdf = SimpleDateFormat("HH", Locale("IR"))
            val netDate = Date(s * 1000)
            sdf.format(netDate)
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }

    fun getRelatedWeatherImage(
        weatherDescription: String,
        dayTime: DayTimes
    ): Int {
        return when (weatherDescription) {
            WeatherConditions.Clear.name -> {
                getClearWeatherBackground(dayTime)
            }
            WeatherConditions.BrokenClouds.name -> {
                R.drawable.ic_0_broken_cloud
            }
            WeatherConditions.FewClouds.name -> {
                getFeaCloudsBackground(dayTime)
            }
            WeatherConditions.Mist.name -> {
                R.drawable.ic_0_mist
            }
            WeatherConditions.Rain.name -> {
                R.drawable.ic_0_rain
            }
            WeatherConditions.ScatteredClouds.name -> {
                R.drawable.ic_0_scattered_clouds
            }
            WeatherConditions.ShowerRain.name -> {
                R.drawable.ic_0_shower_raint
            }
            WeatherConditions.Thunderstorm.name -> {
                R.drawable.ic_0__thunderstorm
            }
            WeatherConditions.Snow.name -> {
                R.drawable.ic_0_snow
            }
            else -> {
                R.drawable.ic_0_clear_sky_morning
            }
        }
    }

    private fun getFeaCloudsBackground(dayTime: DayTimes): Int {
        return when (dayTime) {
            DayTimes.Dawn -> {
                R.drawable.ic_0_few_cloud_dawn
            }
            DayTimes.Morning, DayTimes.Noon -> {
                R.drawable.ic_0_few_cloud_morning
            }
            DayTimes.Evening, DayTimes.Night -> {
                R.drawable.ic_0_few_cloud_evening
            }
            else -> {
                R.drawable.ic_0_clear_sky_morning
            }
        }
    }

    private fun getClearWeatherBackground(dayTime: DayTimes): Int {
        return when (dayTime) {
            DayTimes.Dawn -> {
                R.drawable.ic_0_clear_sky_dawn
            }
            DayTimes.Morning, DayTimes.Noon -> {
                R.drawable.ic_0_clear_sky_morning
            }
            DayTimes.Evening -> {
                R.drawable.ic_0_clear_sky_evening
            }
            DayTimes.Night -> {
                R.drawable.ic_0_clear_sky_night
            }
            else -> {
                R.drawable.ic_0_clear_sky_morning
            }
        }
    }

}