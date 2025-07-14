package com.maverickapps.nutripet.features.pets.utils.timeFormatter

import java.util.Calendar

/**
 * Fake implementation of CalendarFactory for testing purposes.
 */
class FakeCalendarProvider(private val fixedTime: Long) : CalendarFactory {
    override fun getInstance(): Calendar {
        return Calendar.getInstance().apply {timeInMillis = fixedTime}
    }
}