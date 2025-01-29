package com.example.feedm.petsFeature.domain.foodsUseCases

import com.example.feedm.core.domain.model.FoodModel
import com.example.feedm.petsFeature.data.FoodRepository
import javax.inject.Inject

class DeleteFoodUseCase @Inject constructor(private val repository: FoodRepository) {
    suspend operator fun invoke(food: FoodModel) = repository.deleteFood(food)
}