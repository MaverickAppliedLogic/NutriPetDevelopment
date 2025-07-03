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

/*TODO Editar hora de mealNotification cuando una comida es editada*/
    override fun setEvent(time: Long, eventId: Int, extraData: String?) {
        var newTime = time
        val intent = Intent(thisContext, MealNotification::class.java).apply {
            putExtra("notificationId", eventId)
            putExtra("extraData", extraData)
        }
        Log.d("MealNotificationSetter", "Intent creado para MealNotification")
        val pendingIntent = PendingIntent.getBroadcast(
            thisContext,
            eventId,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
        thisAlarmManager.cancel(pendingIntent)
        Log.d("MealNotificationSetter", "PendingIntent creado")
        if (time <= System.currentTimeMillis()) {
            newTime = time + (24*60*60*1000L)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
            && thisAlarmManager.canScheduleExactAlarms()
        ) {
            Log.d("MealNotificationSetter", "Alarma programada para $time")
                thisAlarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    newTime,
                    pendingIntent
                )

        }
    }
}