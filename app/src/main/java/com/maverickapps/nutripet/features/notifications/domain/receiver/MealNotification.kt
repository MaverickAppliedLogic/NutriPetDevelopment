package com.maverickapps.nutripet.features.notifications.domain.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MealNotification: BroadcastReceiver() {

    private val helper = MealNotificationHelper()

    override fun onReceive(context: Context, intent: Intent?) {
        CoroutineScope(Dispatchers.IO).launch {
            Log.d("MealNotification", "Broadcast recibido")
            val notificationId = intent?.getIntExtra("notificationId", 0)
            val extraData = intent?.getStringExtra("extraData")
            Log.d("MealNotification", "NotificationId: $notificationId, extraData: $extraData")
            helper.createNotificationChannel(context)
            helper.createNotification(context, notificationId?:0, extraData)
        }
    }

}