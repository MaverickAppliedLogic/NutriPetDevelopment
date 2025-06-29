package com.maverickapps.nutripet.features.notifications.domain.receiver

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.feedm.R
import com.maverickapps.nutripet.features.notifications.domain.contract.ScheduleNotification
import com.maverickapps.nutripet.features.notifications.domain.contract.ScheduleNotification.Companion.REMINDERS_CHANNEL_ID
import com.maverickapps.nutripet.features.notifications.domain.receiver.MealNotification.Companion.MEAL_NOTIFICATION_ID
import com.maverickapps.nutripet.features.pets.MainPetActivity

class MealNotificationHelper : ScheduleNotification {

    override fun createNotification(context: Context) {
        val intent = Intent(context, MainPetActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val flag = PendingIntent.FLAG_IMMUTABLE
        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, flag)

        val notification = NotificationCompat.Builder(context, REMINDERS_CHANNEL_ID)
            .setSmallIcon(R.mipmap.icon_add_food)
            .setContentTitle("Notification Title")
            .setContentText("Notification Text")
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("Much longer text that cannot fit one line...")
            )
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(MEAL_NOTIFICATION_ID, notification)

    }
}