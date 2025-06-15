package com.maverickapps.nutripet.petsFeature.domain.objectTasks.food.useCase

import com.maverickapps.nutripet.petsFeature.data.FoodRepository
import javax.inject.Inject

class DeleteFoodUseCase @Inject constructor(
    private val foodRepository: FoodRepository,
    private val getFoodUseCase: GetFoodUseCase) {
    suspend operator fun invoke(id: Int) {
        val food = getFoodUseCase(id)
        foodRepository.deleteFood(food)

    }
}