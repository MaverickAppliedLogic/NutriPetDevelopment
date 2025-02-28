package com.example.feedm.petsFeature.utils

import android.icu.util.Calendar

class TimeFormatter() {

    fun formatMillsToString(milliseconds: Long): Triple<String, String, String> {

        val time = Calendar.getInstance().apply { timeInMillis = milliseconds }

        val hour = time.get(Calendar.HOUR)
        val minute = time.get(Calendar.MINUTE)
        val second = time.get(Calendar.SECOND)


        val formattedHours = if (hour<10) "0$hour" else "$hour"
        val formattedMinutes = if (minute < 10) "0$minute" else "$minute"
        val formattedSeconds = if (second < 10) "0$second" else "$second"

        return Triple(formattedHours, formattedMinutes, formattedSeconds)
    }

    fun formatIntToMills(hour: Int, min: Int, secs: Int, mills: Int): Long{

        val time = Calendar.getInstance().apply {
            set(Calendar.HOUR, hour)
            set(Calendar.MINUTE, min)
            set(Calendar.SECOND, secs)
            set(Calendar.MILLISECOND, mills)
        }


        return time.timeInMillis

    }

}
