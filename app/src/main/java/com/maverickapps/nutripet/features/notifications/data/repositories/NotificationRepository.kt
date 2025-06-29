package com.maverickapps.nutripet.features.notifications.data.repositories

import com.maverickapps.nutripet.features.notifications.data.datastore.NotificationDatastore
import com.maverickapps.nutripet.features.notifications.domain.model.ScheduledNotificationModel
import javax.inject.Inject

class NotificationRepository @Inject constructor(
    private val notificationDatastore: NotificationDatastore
)
{
    fun getAllNotifications() : List<ScheduledNotificationModel> {
        return notificationDatastore.getAllScheduledNotifications()
    }

    fun getNotificationById(notificationId: Int) : ScheduledNotificationModel? {
        return notificationDatastore.getScheduledNotificationById(notificationId)
    }

    fun insertAllNotifications(scheduledNotificationsList: List<ScheduledNotificationModel>) {
        notificationDatastore.insertAllScheduledNotifications(scheduledNotificationsList)
    }

    fun insertNotification(scheduledNotification: ScheduledNotificationModel) {
        notificationDatastore.insertScheduledNotification(scheduledNotification)
    }

    fun deleteAllNotifications() {
        notificationDatastore.deleteAllScheduledNotifications()
    }

    fun deleteNotification(notificationId: Int) {
        notificationDatastore.deleteScheduledNotification(notificationId)
    }
}