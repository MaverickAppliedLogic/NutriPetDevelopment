package com.maverickapps.nutripet.features.pets.domain.objectTasks.meal.useCase

import android.util.Log
import com.maverickapps.nutripet.features.events.domain.useCase.schedule.ScheduleNotificationUseCase
import com.maverickapps.nutripet.features.notifications.domain.model.toMealNotificationModel
import com.maverickapps.nutripet.features.notifications.domain.useCase.InsertAllNotificationsUseCase
import com.maverickapps.nutripet.features.notifications.domain.useCase.RefreshScheduleNotificationsUseCase
import com.maverickapps.nutripet.features.pets.domain.objectTasks.meal.model.MealModel
import javax.inject.Inject

class SendMealsToNotificationUseCase @Inject constructor(
    private val getAllMealsUseCase: GetAllMealsUseCase,
    private val insertAllNotificationsUseCase: InsertAllNotificationsUseCase,
    private val scheduleNotificationUseCase: ScheduleNotificationUseCase,
    private val refreshScheduleNotificationsUseCase: RefreshScheduleNotificationsUseCase
) {
    suspend operator fun invoke() {
        val meals = getAllMealsUseCase().toMutableList()
        val notDailyMeals = emptyList<MealModel>().toMutableList()
        meals.forEach {
            Log.d("SendMealsToNotificationUseCase", "Meal: $it")
            if (!it.isDailyMeal) {
                notDailyMeals.add(it)
                meals.remove(it)
            }
        }
        insertAllNotificationsUseCase(meals.map{ it.toMealNotificationModel()})
        refreshScheduleNotificationsUseCase()
        notDailyMeals.forEach {
            scheduleNotificationUseCase(
                it.mealTime,
                it.mealId,
                it.petId.toString(),
                false
            )
        }
    }
}