package com.example.feedm.petsFeature.domain.objectTasks.food.useCase

import com.example.feedm.petsFeature.data.FoodRepository
import javax.inject.Inject

class DeleteFoodUseCase @Inject constructor(
    private val foodRepository: FoodRepository,
    private val getFoodUseCase: GetFoodUseCase) {
    suspend operator fun invoke(id: Int) {
        val food = getFoodUseCase(id)
        foodRepository.deleteFood(food)

    }
}