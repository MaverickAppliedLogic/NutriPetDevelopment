package com.maverickapps.nutripet.features.pets.domain.objectTasks.meal.useCase

import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class UpdateMealsDayChangedUseCase @Inject constructor(
    private val getDailyMealsUseCase: GetDailyMealsUseCase,
    private val editMealUseCase: EditMealUseCase
) {
    suspend operator fun invoke() {
        val dailyMeals = getDailyMealsUseCase()
        coroutineScope {
            for (meal in dailyMeals) {
                editMealUseCase(meal.copy(mealState = 1))
                println("Meal ${meal.mealState} of ${meal.mealId} updated")
            }
        }
    }

}