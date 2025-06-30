package com.maverickapps.nutripet.features.notifications.domain.useCase

import android.util.Log
import com.maverickapps.nutripet.features.notifications.data.repositories.NotificationRepository
import com.maverickapps.nutripet.features.notifications.domain.model.MealNotificationModel
import javax.inject.Inject

class InsertNotificationUseCase @Inject constructor(
    private val repository: NotificationRepository,
) {
    operator fun invoke(notification: MealNotificationModel) {
        Log.d("InsertNotificationUseCase", "Notification inserted: $notification")
        repository.insertNotification(notification)
    }
}
