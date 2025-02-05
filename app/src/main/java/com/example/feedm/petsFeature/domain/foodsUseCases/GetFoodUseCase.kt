package com.example.feedm.petsFeature.domain.foodsUseCases

import com.example.feedm.petsFeature.domain.model.FoodModel
import com.example.feedm.petsFeature.data.FoodRepository
import jakarta.inject.Inject

class GetFoodUseCase @Inject constructor(private val repository: FoodRepository) {

    suspend operator fun invoke(foodId: Int): FoodModel = repository.getFoodById(foodId)
}