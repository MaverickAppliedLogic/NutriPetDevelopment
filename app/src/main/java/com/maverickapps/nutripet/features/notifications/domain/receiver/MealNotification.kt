package com.maverickapps.nutripet.features.notifications.domain.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class MealNotification: BroadcastReceiver() {

    private val helper = MealNotificationHelper()

    companion object{
        const val MEAL_NOTIFICATION_ID = 1
    }

    override fun onReceive(context: Context, intent: Intent?) {
        Log.d("MealNotification", "Broadcast recibido")
        helper.createNotificationChannel(context)
        helper.createNotification(context)
    }

}