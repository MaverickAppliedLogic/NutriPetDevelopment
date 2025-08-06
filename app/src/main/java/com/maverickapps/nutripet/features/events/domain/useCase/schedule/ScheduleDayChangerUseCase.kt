package com.maverickapps.nutripet.features.events.domain.useCase.schedule

import android.icu.util.Calendar
import com.maverickapps.nutripet.features.events.domain.dayChangerEvents.receiver.DayChanger
import com.maverickapps.nutripet.features.events.domain.dayChangerEvents.scheduler.DayChangerScheduler
import javax.inject.Inject

class ScheduleDayChangerUseCase @Inject constructor(
    private val dayChangerScheduler: DayChangerScheduler
) {
    operator fun invoke() {
        val time = Calendar.getInstance()
            .apply {
                set(Calendar.HOUR_OF_DAY, 0)
                set(Calendar.MINUTE, 0)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
            }
            .also { it.add(Calendar.DAY_OF_MONTH, 1) }.timeInMillis

        dayChangerScheduler.scheduleEvent(time, DayChanger.DAY_CHANGE_EVENT_ID)
    }
}