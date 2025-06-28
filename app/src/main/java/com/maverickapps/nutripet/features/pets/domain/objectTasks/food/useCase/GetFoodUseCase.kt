package com.maverickapps.nutripet.features.pets.domain.objectTasks.food.useCase

import com.maverickapps.nutripet.features.pets.domain.objectTasks.food.model.FoodModel
import com.maverickapps.nutripet.features.pets.data.FoodRepository
import jakarta.inject.Inject

class GetFoodUseCase @Inject constructor(private val repository: FoodRepository) {

    suspend operator fun invoke(foodId: Int): FoodModel = repository.getFoodById(foodId)
}