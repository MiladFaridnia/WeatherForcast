package com.faridnia.weatherforcast.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.faridnia.weatherforcast.R
import com.faridnia.weatherforcast.model.DayTimes
import com.faridnia.weatherforcast.model.WeatherConditions
import com.faridnia.weatherforcast.model.onecallresponse.Daily
import com.faridnia.weatherforcast.util.Utils
import kotlinx.android.synthetic.main.city_temp_info_layout.*
import kotlinx.android.synthetic.main.city_temp_next_days_layout.view.*

class WeatherForecastAdapter(private var dailyList: List<Daily>) :
    RecyclerView.Adapter<WeatherForecastAdapter.WeatherInfoHolder>() {

    var onItemClick: ((Daily) -> Unit)? = null

    inner class WeatherInfoHolder(view: View) : RecyclerView.ViewHolder(view) {
        val dayItemNameTextView: TextView = view.dayItemNameTextView
        val dayItemWeatherImageView: ImageView = view.dayItemWeatherImageView
        val dayItemMinMaxTempTextView: TextView = view.dayItemMinMaxTempTextView

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(dailyList[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherInfoHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.city_temp_next_days_layout, parent, false)
        return WeatherInfoHolder(itemView)
    }

    override fun getItemCount(): Int {
        return dailyList.size
    }

    override fun onBindViewHolder(holder: WeatherInfoHolder, position: Int) {
        holder.dayItemNameTextView.text = "Tomorow"
        holder.dayItemMinMaxTempTextView.text =
            getMinMaxTemperature(dailyList[position].temp.min, dailyList[position].temp.max)


        holder.dayItemWeatherImageView.setImageResource(
            getRelatedWeatherImage(
                dailyList[position].weather[0].description,
                Utils().getDayTime(dailyList[position].dt)
            )
        )
    }

    private fun getRelatedWeatherImage(
        description: String,
        dayTime: DayTimes
    ): Int {
        return when (description) {
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

    private fun getMinMaxTemperature(min: Double, max: Double) =
        Utils().getTemperature(min) + "/" +
                Utils().getTemperature(max)

}