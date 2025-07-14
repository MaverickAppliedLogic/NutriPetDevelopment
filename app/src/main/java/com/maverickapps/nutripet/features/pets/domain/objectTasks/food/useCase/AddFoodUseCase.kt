package com.maverickapps.nutripet.features.pets.domain.objectTasks.food.useCase

import com.maverickapps.nutripet.features.pets.data.repositories.FoodRepository
import com.maverickapps.nutripet.features.pets.domain.objectTasks.food.model.FoodModel
import jakarta.inject.Inject

class AddFoodUseCase @Inject constructor(
    private val repository: FoodRepository,
) {
    suspend operator fun invoke(food: FoodModel) {
         repository.addFood(food)
    }
}
