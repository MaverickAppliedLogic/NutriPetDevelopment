package com.maverickapps.nutripet.features.notifications.domain.useCase

import javax.inject.Inject

class DeleteNotificationUseCase @Inject constructor(
    private val getAllNotificationsUseCase: GetAllNotificationsUseCase,
    private val insertAllNotificationsUseCase: InsertAllNotificationsUseCase
) {
    operator fun invoke(notificationId: Int) {
        val notifications = getAllNotificationsUseCase().toMutableList()
        notifications.removeIf { it.notificationId == notificationId }
        insertAllNotificationsUseCase(notifications)
    }
}
