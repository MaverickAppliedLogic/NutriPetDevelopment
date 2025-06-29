package com.maverickapps.nutripet.features.pets.domain.objectTasks.food.useCase

import com.maverickapps.nutripet.features.pets.data.repositories.FoodRepository
import javax.inject.Inject

class DeleteFoodUseCase @Inject constructor(
    private val foodRepository: FoodRepository,
    private val getFoodUseCase: GetFoodUseCase
) {
    suspend operator fun invoke(id: Int) {
        val food = getFoodUseCase(id)
        foodRepository.deleteFood(food)

    }
}