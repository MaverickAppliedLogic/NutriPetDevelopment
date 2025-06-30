package com.maverickapps.nutripet.features.notifications.domain.model

import com.maverickapps.nutripet.features.notifications.domain.contract.ScheduledNotificationModel
import com.maverickapps.nutripet.features.pets.domain.objectTasks.meal.model.MealModel

data class MealNotificationModel(
    override val notificationId: Int,
    override val time: Long
) : ScheduledNotificationModel

fun MealModel.toMealNotificationModel() =
    MealNotificationModel(time = mealTime, notificationId = mealId.hashCode())