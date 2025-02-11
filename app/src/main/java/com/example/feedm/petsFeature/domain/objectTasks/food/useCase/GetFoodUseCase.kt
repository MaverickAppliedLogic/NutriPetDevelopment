package com.example.feedm.petsFeature.domain.objectTasks.food.useCase

import com.example.feedm.petsFeature.domain.objectTasks.food.model.FoodModel
import com.example.feedm.petsFeature.data.FoodRepository
import jakarta.inject.Inject

class GetFoodUseCase @Inject constructor(private val repository: FoodRepository) {

    suspend operator fun invoke(foodId: Int): FoodModel = repository.getFoodById(foodId)
}