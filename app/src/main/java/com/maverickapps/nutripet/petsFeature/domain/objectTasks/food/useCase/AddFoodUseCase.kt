package com.maverickapps.nutripet.petsFeature.domain.objectTasks.food.useCase

import com.maverickapps.nutripet.petsFeature.data.FoodRepository
import com.maverickapps.nutripet.petsFeature.domain.objectTasks.food.model.FoodModel
import jakarta.inject.Inject

class AddFoodUseCase @Inject constructor(
    private val repository: FoodRepository,
) {
    suspend operator fun invoke(food: FoodModel) {
         repository.addFood(food)
    }
}
