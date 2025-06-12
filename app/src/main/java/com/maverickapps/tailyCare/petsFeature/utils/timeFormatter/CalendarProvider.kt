package com.maverickapps.tailyCare.petsFeature.utils.timeFormatter

import java.util.Calendar
import java.util.TimeZone

class CalendarProvider : CalendarFactory {
    override fun getInstance(): Calendar = Calendar.getInstance(TimeZone.getDefault())
}