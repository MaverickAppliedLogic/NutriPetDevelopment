package com.maverickapps.nutripet.features.events.domain.useCase.schedule

import com.maverickapps.nutripet.features.events.domain.dayChangerEvents.receiver.DayChanger
import com.maverickapps.nutripet.features.events.domain.dayChangerEvents.scheduler.DayChangerScheduler
import javax.inject.Inject

class ScheduleDayChangerUseCase @Inject constructor(
    private val dayChangerScheduler: DayChangerScheduler
)
{
    operator fun invoke(time: Long) {
        dayChangerScheduler.scheduleEvent(time, DayChanger.DAY_CHANGE_EVENT_ID)
    }
}