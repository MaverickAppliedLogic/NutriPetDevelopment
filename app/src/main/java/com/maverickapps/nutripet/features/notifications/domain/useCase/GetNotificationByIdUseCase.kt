package com.maverickapps.nutripet.features.notifications.domain.useCase

import com.maverickapps.nutripet.features.notifications.data.repositories.NotificationRepository
import com.maverickapps.nutripet.features.notifications.domain.model.ScheduledNotificationModel
import javax.inject.Inject

class GetNotificationByIdUseCase @Inject constructor(
    private val repository: NotificationRepository
) {
    operator fun invoke(notificationId: Int): ScheduledNotificationModel? {
        return repository.getNotificationById(notificationId)
    }
}
