package com.maverickapps.nutripet.features.pets.domain.objectTasks.meal.useCase

import com.maverickapps.nutripet.features.pets.data.MealsRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class UpdateMealsDayChangedUseCase @Inject constructor(
    private val mealsRepository: MealsRepository
) {
    suspend operator fun invoke() {
        mealsRepository.clearNotDailyMeals()
        val dailyMeals = mealsRepository.getDailyMeals()
        coroutineScope {
            for (meal in dailyMeals) {
               val mealEditing = async {
                    mealsRepository.editMeal(meal.copy(mealState = 1))
                }
                mealEditing.await()
                println("Meal ${meal.mealState} of ${meal.mealId} updated")
            }
        }
    }

}