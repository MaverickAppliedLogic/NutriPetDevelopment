package com.maverickapps.nutripet.features.events.domain.useCase.schedule

import com.maverickapps.nutripet.features.events.domain.notificationsEvents.scheduler.NotificationScheduler
import com.maverickapps.nutripet.features.notifications.domain.contract.ScheduledNotificationModel
import javax.inject.Inject

class RescheduleNotificationsUseCase @Inject constructor(
    private val notificationScheduler: NotificationScheduler
)
{
    operator fun invoke(notifications: List<ScheduledNotificationModel>) {
        notifications.forEach {
            notificationScheduler.scheduleEvent(it.time, it.notificationId)
        }
    }
}