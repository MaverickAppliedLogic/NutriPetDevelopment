package com.maverickapps.tailyCare.petsFeature.utils

import com.maverickapps.tailyCare.petsFeature.utils.timeFormatter.FakeCalendarProvider
import com.maverickapps.tailyCare.petsFeature.utils.timeFormatter.TimeFormatter
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.Calendar

class TimeFormatterTest {

    private val fakeCalendarProvider = FakeCalendarProvider(1624137600000)
    private val timeFormatter = TimeFormatter(fakeCalendarProvider)

    @Test
    fun `formatMillsToInt should convert milliseconds to correct hour and minute`() {

        val mockCalendar = fakeCalendarProvider.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 19)
            set(Calendar.MINUTE, 30)
        }


        val result = timeFormatter.formatMillsToInt(mockCalendar.timeInMillis)

        assertEquals(19, result.first)
        assertEquals(30, result.second)
    }

    @Test
    fun `formatIntToMills should convert hour, minute, second, and millisecond to correct timestamp`() {
        val hour = 14
        val min = 45
        val secs = 30
        val mills = 500

        val expectedCalendar = fakeCalendarProvider.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, min)
            set(Calendar.SECOND, secs)
            set(Calendar.MILLISECOND, mills)
        }

        val result = timeFormatter.formatIntToMills(hour, min, secs, mills)

        assertEquals(expectedCalendar.timeInMillis, result)
    }
}
