package com.maverickapps.nutripet.features.notifications.domain.contract

interface ScheduledNotificationModel {
    val notificationId: Int
    val hour: Int
    val min: Int
    val extraData: String?
}
