package com.maverickapps.nutripet.features.notifications.domain.useCase

import com.maverickapps.nutripet.features.events.domain.useCase.schedule.RescheduleNotificationsUseCase
import javax.inject.Inject

class RefreshScheduleNotificationsUseCase @Inject constructor(
    private val getAllNotificationsUseCase: GetAllNotificationsUseCase,
    private val rescheduleNotificationsUseCase: RescheduleNotificationsUseCase
) {
    suspend operator fun invoke() {
        val notifications = getAllNotificationsUseCase()
        rescheduleNotificationsUseCase(notifications)
    }
}