package com.maverickapps.nutripet.features.pets.domain.objectTasks.meal.useCase

import com.maverickapps.nutripet.features.events.scheduler.NotificationScheduler
import com.maverickapps.nutripet.features.pets.data.repositories.MealsRepository
import com.maverickapps.nutripet.features.pets.domain.objectTasks.meal.model.MealModel
import javax.inject.Inject

class AddMealUseCase @Inject constructor(
    private val mealsRepository: MealsRepository,
    private val notificationScheduler: NotificationScheduler
) {
    suspend operator fun invoke(mealModel: MealModel){
        mealsRepository.addMealForAPet(mealModel)
        notificationScheduler.scheduleEvent(mealModel.mealTime)
    }
}