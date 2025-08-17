package com.maverickapps.nutripet.features.events.domain.dayChangerEvents.scheduler

import android.app.AlarmManager
import android.content.Context
import com.maverickapps.nutripet.features.events.contract.EventScheduler
import com.maverickapps.nutripet.features.events.domain.dayChangerEvents.setter.DayChangerSetter

class DayChangerScheduler(context: Context, alarmManager: AlarmManager) : EventScheduler {

    private val thisContext = context

    private val dayChangerSetter = DayChangerSetter(thisContext, alarmManager)

    override fun scheduleEvent(time: Long, eventId: Int, extraData: String?, needToBeCleared: Boolean) {
            dayChangerSetter.setEvent(time,eventId,null)
    }

}