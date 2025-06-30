package com.maverickapps.nutripet.features.pets.domain.objectTasks.meal.useCase

import android.util.Log
import com.maverickapps.nutripet.features.notifications.domain.model.toMealNotificationModel
import com.maverickapps.nutripet.features.notifications.domain.useCase.InsertNotificationUseCase
import com.maverickapps.nutripet.features.notifications.domain.useCase.RefreshScheduleNotificationsUseCase
import com.maverickapps.nutripet.features.pets.data.repositories.MealsRepository
import com.maverickapps.nutripet.features.pets.domain.objectTasks.meal.model.MealModel
import javax.inject.Inject

class AddMealUseCase @Inject constructor(
    private val mealsRepository: MealsRepository,
    private val insertNotificationUseCase: InsertNotificationUseCase,
    private val refreshScheduleNotificationsUseCase: RefreshScheduleNotificationsUseCase
) {
    suspend operator fun invoke(mealModel: MealModel){
        mealsRepository.addMealForAPet(mealModel)
        Log.d("AddMealUseCase", "Meal added: $mealModel")
        insertNotificationUseCase(mealModel.toMealNotificationModel())
        refreshScheduleNotificationsUseCase()
    }
}