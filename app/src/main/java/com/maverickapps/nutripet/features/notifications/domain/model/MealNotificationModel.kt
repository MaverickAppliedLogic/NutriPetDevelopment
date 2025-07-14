package com.maverickapps.nutripet.features.notifications.domain.model

import android.util.Log
import com.maverickapps.nutripet.features.notifications.domain.contract.ScheduledNotificationModel
import com.maverickapps.nutripet.features.pets.domain.objectTasks.meal.model.MealModel
import com.maverickapps.nutripet.features.pets.utils.timeFormatter.TimeFormatter

data class MealNotificationModel(
    override val notificationId: Int,
    override val hour: Int,
    override val min: Int,
    override val extraData: String?
) : ScheduledNotificationModel

fun MealModel.toMealNotificationModel(): MealNotificationModel {
    val hourAndMin = TimeFormatter().formatMillsToInt(mealTime)
    Log.d("MealNotificationModel", "hour= ${hourAndMin.first}, min = ${hourAndMin.second}")
   return MealNotificationModel(
        notificationId = mealId,
        hour = hourAndMin.first,
        min = hourAndMin.second,
        extraData = petId.toString() )
}


