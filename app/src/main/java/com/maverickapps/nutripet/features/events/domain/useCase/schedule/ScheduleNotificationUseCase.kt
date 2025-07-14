package com.maverickapps.nutripet.features.events.domain.useCase.schedule

import com.maverickapps.nutripet.features.events.domain.notificationsEvents.scheduler.NotificationScheduler
import com.maverickapps.nutripet.features.pets.domain.objectTasks.pet.useCase.GetPetByIdUseCase
import javax.inject.Inject

class ScheduleNotificationUseCase @Inject constructor(
    private val notificationScheduler: NotificationScheduler,
    private val getPetByIdUseCase: GetPetByIdUseCase
)
{
    suspend operator fun invoke(time: Long,
                                eventId: Int,
                                extraData: String?,
                                needToBeCleared: Boolean){
        val petName = getPetByIdUseCase(extraData?.toInt() ?: 0).petName
        notificationScheduler.scheduleEvent(time, eventId, petName, needToBeCleared)
    }
}