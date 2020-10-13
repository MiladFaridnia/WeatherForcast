package com.faridnia.weatherforcast.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.faridnia.weatherforcast.R
import com.faridnia.weatherforcast.model.onecallresponse.Hourly
import com.faridnia.weatherforcast.util.Utils
import kotlinx.android.synthetic.main.city_temp_next_hours_layout.view.*

class NextHourWeatherLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyle, defStyleRes) {

    init {
        LayoutInflater.from(context).inflate(R.layout.city_temp_next_hours_layout, this, true)
    }

    fun setNextHourData(hourly: Hourly) {
        hourItemWeatherImageView.setImageResource(R.drawable.ic_0_few_cloud_morning)
        hourItemTemperatureTextView.text = Utils().getTemperature(hourly.temp)
        hourItemTimeOfDayTextView.text =  Utils().getHourFromDate(hourly.dt)
    }

}