package com.maverickapps.nutripet.features.events.domain.useCase.schedule

import android.icu.util.Calendar
import android.util.Log
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
          .also {
                it.add(Calendar.DATE, 1) }

        Log.d("ScheduleDayChangerUseCase",
            "invoke: ${time.get(Calendar.HOUR_OF_DAY)}:${time.get(Calendar.MINUTE)}," +
                    " ${time.get(Calendar.DAY_OF_YEAR)}")
        dayChangerScheduler.scheduleEvent(time.timeInMillis, DayChanger.DAY_CHANGE_EVENT_ID)
    }
}