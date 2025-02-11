package com.example.feedm.petsFeature.domain.objectTasks.food.useCase

import com.example.feedm.petsFeature.domain.objectTasks.food.model.FoodModel
import com.example.feedm.petsFeature.data.FoodRepository
import javax.inject.Inject

class DeleteFoodUseCase @Inject constructor(private val foodRepository: FoodRepository) {
    suspend operator fun invoke(food: FoodModel) {
        foodRepository.deleteFood(food)
    }
}