package com.maverickapps.nutripet.features.notifications.domain.contract

interface ScheduledNotificationModel{
    val notificationId: Int
    val time: Long
    val extraData: String?
}
