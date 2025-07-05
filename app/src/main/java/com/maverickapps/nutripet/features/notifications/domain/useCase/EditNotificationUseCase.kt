package com.maverickapps.nutripet.features.notifications.domain.useCase

import com.maverickapps.nutripet.features.notifications.domain.model.MealNotificationModel
import javax.inject.Inject

class EditNotificationUseCase @Inject constructor(
    private val getAllNotificationsUseCase: GetAllNotificationsUseCase,
    private val insertAllNotificationsUseCase: InsertAllNotificationsUseCase
) {
   operator fun invoke(notification: MealNotificationModel) {
       val notifications = getAllNotificationsUseCase().toMutableList()
       notifications.removeIf { it.notificationId == notification.notificationId }
       notifications.add(notification)
       insertAllNotificationsUseCase(notifications)
   }
}