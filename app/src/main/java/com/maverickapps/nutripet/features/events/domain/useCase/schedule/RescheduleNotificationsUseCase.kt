package com.maverickapps.nutripet.features.events.domain.useCase.schedule

import android.util.Log
import com.maverickapps.nutripet.features.notifications.domain.contract.ScheduledNotificationModel
import javax.inject.Inject

class RescheduleNotificationsUseCase @Inject constructor(
    private val scheduleNotificationUseCase: ScheduleNotificationUseCase
)
{
    suspend operator fun invoke(notifications: List<ScheduledNotificationModel>) {
        notifications.forEach {
            Log.d("RescheduleNotificationsUseCase", "Notification to reschedule: ${it.extraData}")
            Log.d("RescheduleNotificationsUseCase", "Rescheduling notification: ${it.notificationId}")
            scheduleNotificationUseCase(it.time, it.notificationId, it.extraData, true)
        }
    }
}