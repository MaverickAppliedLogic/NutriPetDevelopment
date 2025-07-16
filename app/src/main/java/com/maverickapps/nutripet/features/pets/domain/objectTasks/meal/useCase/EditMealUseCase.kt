package com.maverickapps.nutripet.features.pets.domain.objectTasks.meal.useCase

import android.util.Log
import com.maverickapps.nutripet.features.events.domain.useCase.schedule.CancelNotificationUseCase
import com.maverickapps.nutripet.features.pets.data.repositories.MealsRepository
import com.maverickapps.nutripet.features.pets.domain.objectTasks.meal.model.MealModel
import javax.inject.Inject

class EditMealUseCase @Inject constructor(
    private val mealRepository: MealsRepository,
    private val sendMealsToNotificationUseCase: SendMealsToNotificationUseCase,
    private val cancelNotificationUseCase: CancelNotificationUseCase
) {

    suspend operator fun invoke(meal: MealModel) {
        mealRepository.editMeal(meal)
        Log.d("EditMealUseCase", "Meal edited: $meal")
        if (!meal.isDailyMeal) {
            cancelNotificationUseCase(meal.mealId, meal.petId.toString())
        }
        sendMealsToNotificationUseCase()
    }

}