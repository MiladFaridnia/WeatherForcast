package com.faridnia.weatherforcast.util

import com.faridnia.weatherforcast.model.DayTimes
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
}