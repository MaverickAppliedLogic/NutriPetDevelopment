package com.example.feedm.petsFeature.utils.timeFormatter

import android.icu.util.Calendar
import java.util.TimeZone

class TimeFormatter(private val calendarProvider: CalendarFactory = CalendarProvider()) {

        val currentTime = calendarProvider.getInstance().timeInMillis

        fun formatMillsToInt(milliseconds: Long): Pair<Int, Int> {

            val time = calendarProvider.getInstance().apply {
                timeZone = TimeZone.getDefault()
                timeInMillis = milliseconds }

            val hour = time.get(Calendar.HOUR_OF_DAY)
            val minute = time.get(Calendar.MINUTE)

            return Pair(hour,minute)
        }

        fun formatIntToMills(hour: Int, min: Int, secs: Int, mills: Int): Long {

            val time = calendarProvider.getInstance().apply {
                timeZone = TimeZone.getDefault()
                set(Calendar.HOUR_OF_DAY, hour)
                set(Calendar.MINUTE, min)
                set(Calendar.SECOND, secs)
                set(Calendar.MILLISECOND, mills)
            }
            println("Time in mills: ${time.timeInMillis}")
            return time.timeInMillis
        }



}
