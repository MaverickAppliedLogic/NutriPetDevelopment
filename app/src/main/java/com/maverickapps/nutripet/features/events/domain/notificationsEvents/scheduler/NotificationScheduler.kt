package com.maverickapps.nutripet.features.events.domain.notificationsEvents.scheduler

import android.app.AlarmManager
import android.content.Context
import android.util.Log
import com.maverickapps.nutripet.features.events.contract.EventScheduler
import com.maverickapps.nutripet.features.events.domain.notificationsEvents.setter.MealNotificationSetter

class NotificationScheduler(context: Context, alarmManager: AlarmManager): EventScheduler {

    private val thisContext = context

    private val mealNotificationSetter = MealNotificationSetter(thisContext, alarmManager)

    override fun scheduleEvent(time: Long, eventId: Int) {
            Log.d("NotificationScheduler", "Scheduling event at $time")
            mealNotificationSetter.setEvent(time, eventId)
    }
}

