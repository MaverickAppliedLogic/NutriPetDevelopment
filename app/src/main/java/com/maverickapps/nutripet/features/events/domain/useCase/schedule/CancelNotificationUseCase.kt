package com.maverickapps.nutripet.features.events.domain.useCase.schedule

import com.maverickapps.nutripet.features.events.domain.notificationsEvents.scheduler.NotificationScheduler
import javax.inject.Inject

class CancelNotificationUseCase @Inject constructor(
    private val notificationScheduler: NotificationScheduler
)
{
    operator fun invoke(eventId: Int, extraData: String?) =
        notificationScheduler.cancelEvent(eventId, extraData)
}