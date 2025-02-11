package com.example.feedm.petsFeature.domain.objectTasks.meal.useCase

import com.example.feedm.petsFeature.data.MealsRepository
import javax.inject.Inject

class UpdateMealsDayChangedUseCase @Inject constructor(
    private val mealsRepository: MealsRepository
) {
    suspend operator fun invoke()= mealsRepository.clearAllMeals()
}