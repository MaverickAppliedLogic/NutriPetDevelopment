package com.maverickapps.nutripet.features.notifications.domain.useCase

import com.maverickapps.nutripet.features.notifications.data.repositories.NotificationRepository
import com.maverickapps.nutripet.features.notifications.domain.model.ScheduledNotificationModel
import javax.inject.Inject

class GetAllNotificationsUseCase @Inject constructor(
    private val repository: NotificationRepository
) {
    operator fun invoke(): List<ScheduledNotificationModel> {
        return repository.getAllNotifications()
    }
}
