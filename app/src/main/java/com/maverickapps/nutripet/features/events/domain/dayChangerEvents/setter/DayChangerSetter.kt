package com.maverickapps.nutripet.features.events.domain.dayChangerEvents.setter

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import com.maverickapps.nutripet.features.events.contract.EventSetter
import com.maverickapps.nutripet.features.events.domain.dayChangerEvents.receiver.DayChanger

class DayChangerSetter(
    context: Context,
    alarmManager: AlarmManager
) : EventSetter {

    private val thisContext = context
    private val thisAlarmManager = alarmManager

    override fun setEvent(time: Long, eventId: Int, extraData: String?, needToBeCleared: Boolean) {
        val intent = Intent(thisContext, DayChanger::class.java)
        Log.d("DayChangerSetter", "Intent creado para DayChanger")
        val pendingIntent = PendingIntent.getBroadcast(
            thisContext,
            eventId,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
        Log.d("DayChangerSetter", "PendingIntent creado")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
            && thisAlarmManager.canScheduleExactAlarms()
        ) {
            Log.d("DayChangerSetter", "Alarma programada para $time")
            thisAlarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                time,
                AlarmManager.INTERVAL_DAY,
                pendingIntent
            )
        }    }
}