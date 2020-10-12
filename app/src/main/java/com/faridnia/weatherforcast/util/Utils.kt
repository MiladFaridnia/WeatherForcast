package com.faridnia.weatherforcast.util

import com.faridnia.weatherforcast.model.DayTimes
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


}