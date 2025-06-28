package com.maverickapps.nutripet.features.pets.utils.timeFormatter

import java.util.Calendar

interface CalendarFactory {
    fun getInstance(): Calendar
}