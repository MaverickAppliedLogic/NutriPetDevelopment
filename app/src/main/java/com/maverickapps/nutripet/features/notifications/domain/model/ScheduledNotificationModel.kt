package com.maverickapps.nutripet.features.notifications.domain.model

data class ScheduledNotificationModel(
    val notificationId: Int,
    val mealId: Int,
    val time: Long
)
