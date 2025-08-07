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
        Log.d("SendMealsToNotificationUseCase", "Meals: $meals")
        val notDailyMeals = emptyList<MealModel>().toMutableList()
        val iterator = meals.iterator()
        while (iterator.hasNext()) {
            val meal = iterator.next()
            Log.d("SendMealsToNotificationUseCase", "Meal: $meal")
            if (meal.mealState == 0) {
                iterator.remove()
                Log.d("SendMealsToNotificationUseCase", "Meal removed: $meal")
            } else {
                if (!meal.isDailyMeal) {
                    iterator.remove()
                    Log.d("SendMealsToNotificationUseCase", "Meal removed: $meal")
                    notDailyMeals.add(meal)
                }
            }
        }
        insertAllNotificationsUseCase(meals.map { it.toMealNotificationModel() })
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