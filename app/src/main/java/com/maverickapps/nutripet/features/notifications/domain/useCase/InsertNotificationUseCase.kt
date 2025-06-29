package com.maverickapps.nutripet.features.notifications.domain.useCase

import com.maverickapps.nutripet.features.notifications.data.repositories.NotificationRepository
import com.maverickapps.nutripet.features.notifications.domain.model.ScheduledNotificationModel
import javax.inject.Inject

class InsertNotificationUseCase @Inject constructor(
    private val repository: NotificationRepository
) {
    operator fun invoke(notification: ScheduledNotificationModel) {
        repository.insertNotification(notification)
    }
}
