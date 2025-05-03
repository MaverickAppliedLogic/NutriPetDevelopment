package com.example.feedm.petsFeature.utils.timeFormatter

import android.icu.util.Calendar

class TimeFormatter(private val calendarProvider: CalendarFactory = CalendarProvider()) {

        val currentTime = calendarProvider.getInstance().timeInMillis

        fun formatMillsToInt(milliseconds: Long): Pair<Int, Int> {

            val time = calendarProvider.getInstance().apply { timeInMillis = milliseconds }

            val hour = time.get(Calendar.HOUR_OF_DAY)
            val minute = time.get(Calendar.MINUTE)

            return Pair(hour,minute)
        }

        fun formatIntToMills(hour: Int, min: Int, secs: Int, mills: Int): Long {

            val time = calendarProvider.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, hour)
                set(Calendar.MINUTE, min)
                set(Calendar.SECOND, secs)
                set(Calendar.MILLISECOND, mills)
            }
            return time.timeInMillis
        }



}
