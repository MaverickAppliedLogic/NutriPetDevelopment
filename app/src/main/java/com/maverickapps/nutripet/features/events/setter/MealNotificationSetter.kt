package com.maverickapps.nutripet.features.events.setter

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.content.ContextCompat.getSystemService
import com.maverickapps.nutripet.features.events.contract.EventSetter
import com.maverickapps.nutripet.features.notifications.domain.receiver.MealNotification

class MealNotificationSetter(context: Context): EventSetter {

    private val thisContext = context

    override fun setEvent(time: Long) {
        val intent = Intent(thisContext, MealNotification::class.java)
        Log.d("MealNotificationSetter", "Intent creado para MealNotification")
        val pendingIntent = PendingIntent.getBroadcast(
            thisContext,
            MealNotification.MEAL_NOTIFICATION_ID,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)
        Log.d("MealNotificationSetter", "PendingIntent creado")
        /**TODO falta pedir el permiso POST y eliminar las notificaciones programadas cuando se eliminan los meals
         */
        val alarmManager = getSystemService(thisContext, AlarmManager::class.java) as AlarmManager
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && !alarmManager.canScheduleExactAlarms()){
            thisContext.startActivity(
                Intent(android.provider.Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM).
            apply { flags = Intent.FLAG_ACTIVITY_NEW_TASK })
        }
        else{
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, time, pendingIntent)
            Log.d("MealNotificationSetter", "Alarma programada para: $time")
        }
    }
}