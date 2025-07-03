package com.maverickapps.nutripet.features.events.domain.useCase.schedule

import android.util.Log
import com.maverickapps.nutripet.features.events.domain.notificationsEvents.scheduler.NotificationScheduler
import com.maverickapps.nutripet.features.notifications.domain.contract.ScheduledNotificationModel
import com.maverickapps.nutripet.features.pets.domain.objectTasks.pet.useCase.GetPetByIdUseCase
import javax.inject.Inject

class RescheduleNotificationsUseCase @Inject constructor(
    private val notificationScheduler: NotificationScheduler,
    private val getPetByIdUseCase: GetPetByIdUseCase
)
{
    suspend operator fun invoke(notifications: List<ScheduledNotificationModel>) {
        notifications.forEach {
            Log.d("RescheduleNotificationsUseCase", "Notification to reschedule: ${it.extraData}")
            val petName = getPetByIdUseCase(it.extraData?.toInt() ?: 0).petName
            Log.d("RescheduleNotificationsUseCase", "Rescheduling notification: ${it.notificationId}")
            notificationScheduler.scheduleEvent(it.time, it.notificationId, petName)
        }
    }
}