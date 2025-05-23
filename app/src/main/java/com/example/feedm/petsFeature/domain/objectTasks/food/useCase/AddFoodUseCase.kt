package com.example.feedm.petsFeature.domain.objectTasks.food.useCase

import com.example.feedm.petsFeature.data.FoodRepository
import com.example.feedm.petsFeature.domain.objectTasks.food.model.FoodModel
import jakarta.inject.Inject

class AddFoodUseCase @Inject constructor(
    private val repository: FoodRepository,
) {
    suspend operator fun invoke(food: FoodModel) {
         repository.addFood(food)
    }
}
