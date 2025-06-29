package com.maverickapps.nutripet.features.events.scheduler.setter

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import com.maverickapps.nutripet.features.events.contract.EventSetter
import com.maverickapps.nutripet.features.notifications.domain.receiver.MealNotification

class MealNotificationSetter(context: Context, alarmManager: AlarmManager) : EventSetter {

    private val thisContext = context
    private val thisAlarmManager = alarmManager

    override fun setEvent(time: Long) {
        val intent = Intent(thisContext, MealNotification::class.java)
        Log.d("MealNotificationSetter", "Intent creado para MealNotification")
        val pendingIntent = PendingIntent.getBroadcast(
            thisContext,
            MealNotification.MEAL_NOTIFICATION_ID,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
        Log.d("MealNotificationSetter", "PendingIntent creado")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
            && thisAlarmManager.canScheduleExactAlarms()
        ) {
            Log.d("MealNotificationSetter", "Alarma programada para $time")
            thisAlarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                time,
                pendingIntent
            )
        }
    }
}