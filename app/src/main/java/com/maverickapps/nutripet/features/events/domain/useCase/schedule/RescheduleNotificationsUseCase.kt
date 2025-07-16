package com.maverickapps.nutripet.features.events.domain.useCase.schedule

import android.util.Log
import com.maverickapps.nutripet.features.notifications.domain.contract.ScheduledNotificationModel
import java.util.Calendar
import javax.inject.Inject

class RescheduleNotificationsUseCase @Inject constructor(
    private val scheduleNotificationUseCase: ScheduleNotificationUseCase
)
{
    suspend operator fun invoke(notifications: List<ScheduledNotificationModel>) {
        notifications.forEach {
            val todayNotificationTime = Calendar.getInstance().apply{
                set(Calendar.HOUR_OF_DAY, it.hour)
                set(Calendar.MINUTE, it.min)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
            }.timeInMillis

            Log.d("RescheduleNotificationsUseCase", "Notification to reschedule: ${it.notificationId}")
            Log.d("RescheduleNotificationsUseCase",
                "Rescheduling notification: ${it.hour}:${it.min}")
            scheduleNotificationUseCase(
                todayNotificationTime,
                it.notificationId,
                it.extraData,
                true)
        }
    }
}