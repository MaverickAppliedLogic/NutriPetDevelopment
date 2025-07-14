package com.maverickapps.nutripet.features.notifications.data.repositories

import com.maverickapps.nutripet.features.notifications.data.datastore.NotificationDatastore
import com.maverickapps.nutripet.features.notifications.domain.model.MealNotificationModel
import javax.inject.Inject

class NotificationRepository @Inject constructor(
    private val notificationDatastore: NotificationDatastore
)
{
    fun getAllNotifications() : List<MealNotificationModel> {
        return notificationDatastore.getAllScheduledNotifications()
    }

    fun getNotificationById(notificationId: Int) : MealNotificationModel? {
        return notificationDatastore.getScheduledNotificationById(notificationId)
    }

    fun insertAllNotifications(scheduledNotificationsList: List<MealNotificationModel>) {
        notificationDatastore.insertAllScheduledNotifications(scheduledNotificationsList)
    }


    fun deleteAllNotifications() {
        notificationDatastore.deleteAllScheduledNotifications()
    }

}