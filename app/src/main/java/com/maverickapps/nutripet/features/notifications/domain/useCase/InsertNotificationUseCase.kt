package com.maverickapps.nutripet.features.notifications.domain.useCase

import android.util.Log
import com.maverickapps.nutripet.features.notifications.domain.model.MealNotificationModel
import javax.inject.Inject

class InsertNotificationUseCase @Inject constructor(
    private val getAllNotificationsUseCase: GetAllNotificationsUseCase,
    private val insertAllNotificationsUseCase: InsertAllNotificationsUseCase,
) {
    operator fun invoke(notification: MealNotificationModel) {
        Log.d("InsertNotificationUseCase", "Notification inserted: $notification")
        val notifications = getAllNotificationsUseCase().toMutableList()
        notifications.add(notification)
        insertAllNotificationsUseCase(notifications)
    }
}
