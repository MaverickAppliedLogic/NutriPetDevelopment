package com.maverickapps.nutripet.features.pets.domain.objectTasks.meal.useCase

import android.util.Log
import com.maverickapps.nutripet.features.pets.data.repositories.MealsRepository
import com.maverickapps.nutripet.features.pets.domain.objectTasks.meal.model.MealModel
import javax.inject.Inject


/**
 * Use case responsible for adding a new meal for a pet.
 *
 * This use case performs the following actions:
 * 1. Inserts the meal into the meals repository and retrieves the generated ID.
 * 2. If the meal is marked as daily (`isDailyMeal`):
 *    - Inserts a persistent notification associated with the meal.
 *    - Refreshes all scheduled notifications to ensure consistency.
 * 3. If the meal is not daily:
 *    - Schedules a one-time notification at the specified meal time.
 *
 * `mealModel` Data model representing the meal to be added.
 */

class AddMealUseCase @Inject constructor(
    private val mealsRepository: MealsRepository,
    private val sendMealsToNotificationUseCase: SendMealsToNotificationUseCase
) {
    suspend operator fun invoke(mealModel: MealModel){
        mealsRepository.addMealForAPet(mealModel)
        Log.d("AddMealUseCase", "Meal added: $mealModel")
        sendMealsToNotificationUseCase()
    }
}