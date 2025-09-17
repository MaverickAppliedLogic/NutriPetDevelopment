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
        val filteredMeals = filterMeals(meals)

        Log.d("SendMealsToNotificationUseCase", "Meals: $meals")
        insertAllNotificationsUseCase(filteredMeals.first.map { it.toMealNotificationModel() })
        refreshScheduleNotificationsUseCase()

        filteredMeals.second.forEach {
            scheduleNotificationUseCase(
                it.mealTime,
                it.mealId,
                it.petId.toString(),
                false
            )
        }
    }


    /**
     * Divide meals into two lists (daily meals and not daily meals) to make difference between
     * meals that must being stored for daily notifications from one time meals, and remove meals
     * already consumed (`mealState = 0`) to avoid sending notifications for them.
     */
    private fun filterMeals(meals: MutableList<MealModel>): Pair<List<MealModel>, List<MealModel>>{
        val notDailyMeals = emptyList<MealModel>().toMutableList()
        val iterator = meals.iterator()

        while (iterator.hasNext()) {
            val meal = iterator.next()

            if(meal.mealState != 0 && !meal.isDailyMeal){
                iterator.remove()
                Log.d("SendMealsToNotificationUseCase", "Meal removed: $meal")
                notDailyMeals.add(meal)
            }
            else if (meal.mealState == 0){
                iterator.remove()
                Log.d("SendMealsToNotificationUseCase", "Meal removed: $meal")
            }
        }

        return Pair(meals, notDailyMeals)
    }
}