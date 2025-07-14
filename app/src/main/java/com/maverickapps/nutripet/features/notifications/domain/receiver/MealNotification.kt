package com.maverickapps.nutripet.features.notifications.domain.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class MealNotification: BroadcastReceiver() {

    private val helper = MealNotificationHelper()

    override fun onReceive(context: Context, intent: Intent?) {
        Log.d("MealNotification", "Broadcast recibido")
        val notificationId = intent?.getIntExtra("notificationId", 0)
        val extraData = intent?.getStringExtra("extraData")
        Log.d("MealNotification", "NotificationId: $notificationId, extraData: $extraData")
        helper.createNotificationChannel(context)
        helper.createNotification(context, notificationId?:0, extraData)
    }

}