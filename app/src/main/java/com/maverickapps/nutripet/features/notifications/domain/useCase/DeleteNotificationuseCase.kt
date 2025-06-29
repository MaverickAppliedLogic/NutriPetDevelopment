package com.maverickapps.nutripet.features.notifications.domain.useCase

import com.maverickapps.nutripet.features.notifications.data.repositories.NotificationRepository
import javax.inject.Inject

class DeleteNotificationUseCase @Inject constructor(
    private val repository: NotificationRepository
) {
    operator fun invoke(notificationId: Int) {
        repository.deleteNotification(notificationId)
    }
}
