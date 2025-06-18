package com.maverickapps.nutripet.petsFeature.domain.objectTasks.meal.useCase

import com.maverickapps.nutripet.petsFeature.data.MealsRepository
import javax.inject.Inject

class GetMealByIdUseCase @Inject constructor(private val repository: MealsRepository) {
    suspend operator fun invoke(mealId: Int) = repository.getMealById(mealId)
}