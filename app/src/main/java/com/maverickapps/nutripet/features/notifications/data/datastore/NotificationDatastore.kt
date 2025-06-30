package com.maverickapps.nutripet.features.notifications.data.datastore

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.maverickapps.nutripet.features.notifications.domain.model.MealNotificationModel
import java.io.File

class NotificationDatastore(private val notificationsFile: File) {
    private val gson = Gson()

    fun getAllScheduledNotifications(): List<MealNotificationModel> {
        return try {
            val json = notificationsFile.readText()
            if (json.isBlank()) return emptyList()
            val listType = object : TypeToken<List<MealNotificationModel>>() {}.type
            gson.fromJson<List<MealNotificationModel>>(json, listType) ?: emptyList()
        } catch (_: Exception) {
            emptyList()
        }
    }

    fun getScheduledNotificationById(notificationId: Int): MealNotificationModel? {
        val scheduledNotifications = getAllScheduledNotifications()
        return scheduledNotifications.find { it.notificationId == notificationId }
    }

    fun insertAllScheduledNotifications(
        scheduledNotificationsList: List<MealNotificationModel>
    ) {
        val json = gson.toJson(scheduledNotificationsList)
        notificationsFile.writeText(json)
    }

    fun insertScheduledNotification(scheduledNotification: MealNotificationModel) {
        val updatedScheduledNotifications = getAllScheduledNotifications().toMutableList()
        updatedScheduledNotifications.add(scheduledNotification)
        val json = gson.toJson(updatedScheduledNotifications)
        notificationsFile.writeText(json)
    }

    fun deleteAllScheduledNotifications() {
        notificationsFile.writeText("")
    }

    fun deleteScheduledNotification(notificationId: Int) {
        val updatedScheduledNotifications = getAllScheduledNotifications().toMutableList()
        updatedScheduledNotifications.removeIf { it.notificationId == notificationId }
        val json = gson.toJson(updatedScheduledNotifications)
        notificationsFile.writeText(json)
    }
}
