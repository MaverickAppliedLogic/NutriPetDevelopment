package com.maverickapps.nutripet.features.notifications.data.datastore

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.maverickapps.nutripet.features.notifications.domain.model.ScheduledNotificationModel
import java.io.File
import java.io.FileWriter

class NotificationDatastore(private val notificationsFile: File) {
    private val gson = Gson()

    fun getAllScheduledNotifications(): List<ScheduledNotificationModel> {
        val scheduledNotifications: List<ScheduledNotificationModel>
        try {
            val json = notificationsFile.readText()
            val listType: java.lang.reflect.Type =
                object : TypeToken<List<ScheduledNotificationModel>>() {}.type
            scheduledNotifications = gson.fromJson(json, listType)
        } catch (_: NullPointerException) {
            return emptyList()
        }
        return scheduledNotifications
    }

    fun getScheduledNotificationById(notificationId: Int): ScheduledNotificationModel? {
        val scheduledNotifications = getAllScheduledNotifications()
        return scheduledNotifications.find { it.notificationId == notificationId }
    }

    fun insertAllScheduledNotifications(
        scheduledNotificationsList: List<ScheduledNotificationModel>
    ) {
        val json = gson.toJson(scheduledNotificationsList)
        FileWriter(notificationsFile).use { writer ->
            writer.write(json)
        }
    }

    fun insertScheduledNotification(scheduledNotification: ScheduledNotificationModel) {
        val updatedScheduledNotifications = getAllScheduledNotifications().toMutableList()
        updatedScheduledNotifications.add(scheduledNotification)
        val json = gson.toJson(updatedScheduledNotifications)
        FileWriter(notificationsFile, true).use { writer ->
            writer.write(json)
        }

    }

    fun deleteAllScheduledNotifications() {
        FileWriter(notificationsFile).use { writer ->
            writer.write("")
        }
    }

    fun deleteScheduledNotification(notificationId: Int) {
        val updatedScheduledNotifications = getAllScheduledNotifications().toMutableList()
        updatedScheduledNotifications.removeIf { it.notificationId == notificationId }
        val json = gson.toJson(updatedScheduledNotifications)
        FileWriter(notificationsFile).use { writer ->
            writer.write(json)
        }
    }
}