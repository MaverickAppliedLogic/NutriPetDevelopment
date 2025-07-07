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
            val notificationTime = Calendar.getInstance().apply{ timeInMillis = it.time }
            val hour = notificationTime.get(Calendar.HOUR_OF_DAY)
            val minute = notificationTime.get(Calendar.MINUTE)

            val todayNotificationTime = Calendar.getInstance().apply{
                set(Calendar.HOUR_OF_DAY, hour)
                set(Calendar.MINUTE, minute)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
            }.timeInMillis

            Log.d("RescheduleNotificationsUseCase", "Notification to reschedule: ${it.extraData}")
            Log.d("RescheduleNotificationsUseCase", "Rescheduling notification: ${it.notificationId}")
            scheduleNotificationUseCase(
                todayNotificationTime,
                it.notificationId,
                it.extraData,
                true)
        }
    }
}