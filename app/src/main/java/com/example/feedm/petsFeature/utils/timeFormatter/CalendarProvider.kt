package com.example.feedm.petsFeature.utils.timeFormatter

import java.util.Calendar

class CalendarProvider : CalendarFactory {
    override fun getInstance(): Calendar = Calendar.getInstance()
}