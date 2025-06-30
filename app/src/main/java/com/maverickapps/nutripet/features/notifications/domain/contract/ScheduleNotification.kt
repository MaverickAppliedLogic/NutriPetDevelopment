package com.maverickapps.nutripet.features.notifications.domain.contract

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.content.ContextCompat.getSystemService

interface ScheduleNotification {

    companion object {
        const val REMINDERS_CHANNEL_ID = "reminders_channel_id"
    }

     fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                REMINDERS_CHANNEL_ID,
                "Meal Reminders",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Reminders for your pets"
            }

            val notificationManager: NotificationManager =
                getSystemService(context, NotificationManager::class.java) as NotificationManager

            notificationManager.createNotificationChannel(channel)
        }
    }

    fun createNotification(context: Context, notificationId: Int)

}