package com.maverickapps.nutripet.features.pets.domain.objectTasks.meal.useCase

import com.maverickapps.nutripet.features.pets.data.MealsRepository
import javax.inject.Inject

class GetMealByIdUseCase @Inject constructor(private val repository: MealsRepository) {
    suspend operator fun invoke(mealId: Int) = repository.getMealById(mealId)
}