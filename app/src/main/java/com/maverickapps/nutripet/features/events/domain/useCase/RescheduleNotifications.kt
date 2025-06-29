package com.maverickapps.nutripet.features.events.domain.useCase

import com.maverickapps.nutripet.features.events.scheduler.setter.MealNotificationSetter
import com.maverickapps.nutripet.features.notifications.domain.useCase.GetAllNotificationsUseCase
import javax.inject.Inject

class RescheduleNotifications @Inject constructor(
    private val getAllNotificationsUseCase: GetAllNotificationsUseCase,
    private val MealNotificationSetter: MealNotificationSetter
)
{
/*TODO implement reschedule triggering when meals changes*/
}