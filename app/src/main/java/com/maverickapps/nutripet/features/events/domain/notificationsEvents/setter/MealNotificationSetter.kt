package com.maverickapps.nutripet.features.events.domain.notificationsEvents.setter

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import com.maverickapps.nutripet.features.events.contract.EventSetter
import com.maverickapps.nutripet.features.notifications.domain.receiver.MealNotification

class MealNotificationSetter(
    context: Context, alarmManager: AlarmManager
) : EventSetter {

    private val thisContext = context
    private val thisAlarmManager = alarmManager


    override fun setEvent(
        time: Long,
        eventId: Int,
        extraData: String?) {
        val alarmClockInfo = AlarmManager.AlarmClockInfo(time, null)
        val intent = Intent(thisContext, MealNotification::class.java).apply {
            putExtra("notificationId", eventId)
            putExtra("extraData", extraData)
        }
        Log.d("MealNotificationSetter", "Intent creado para MealNotification $eventId")
        val pendingIntent = PendingIntent.getBroadcast(
            thisContext,
            eventId,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
        Log.d("MealNotificationSetter", "PendingIntent creado $pendingIntent")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
            && thisAlarmManager.canScheduleExactAlarms()
            && time > System.currentTimeMillis()
        ) {
            Log.d("MealNotificationSetter", "Alarma programada para $time")
            thisAlarmManager.setAlarmClock(alarmClockInfo, pendingIntent)
        }
    }

    fun cancelEvent(
        eventId: Int,
        extraData: String?,
    ) {
        val intent = Intent(thisContext, MealNotification::class.java).apply {
            putExtra("notificationId", eventId)
            putExtra("extraData", extraData)
        }
        Log.d("MealNotificationSetter", "Intent creado para eliminar MealNotification $eventId")
        val pendingIntent = PendingIntent.getBroadcast(
            thisContext,
            eventId,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
        Log.d("MealNotificationSetter", "PendingIntent creado eliminar $pendingIntent")
        thisAlarmManager.cancel(pendingIntent)
        Log.d("MealNotificationSetter", "Alarma cancelada $eventId")
    }
}