package com.maverickapps.nutripet.features.pets.domain.objectTasks.meal.useCase

import com.maverickapps.nutripet.features.events.domain.useCase.schedule.CancelNotificationUseCase
import com.maverickapps.nutripet.features.pets.data.repositories.MealsRepository
import com.maverickapps.nutripet.features.pets.domain.objectTasks.meal.model.MealModel
import javax.inject.Inject

class DeleteMealUseCase @Inject constructor(
    private val mealsRepository: MealsRepository,
    private val cancelNotificationUseCase: CancelNotificationUseCase,
    private val sendMealsToNotificationUseCase: SendMealsToNotificationUseCase
){
    suspend operator fun invoke(meal: MealModel){
        mealsRepository.deleteMealForAPet(meal.mealId)
        cancelNotificationUseCase(meal.mealId, meal.petId.toString())
        sendMealsToNotificationUseCase()
    }
}