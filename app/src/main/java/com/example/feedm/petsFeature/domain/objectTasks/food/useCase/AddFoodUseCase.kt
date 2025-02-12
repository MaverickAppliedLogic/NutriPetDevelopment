package com.example.feedm.petsFeature.domain.objectTasks.food.useCase

import com.example.feedm.petsFeature.domain.objectTasks.food.model.FoodModel
import com.example.feedm.petsFeature.data.FoodRepository
import com.example.feedm.petsFeature.domain.objectTasks.petFood.useCase.AddFoodToPetUseCase
import jakarta.inject.Inject

class AddFoodUseCase @Inject constructor(
    private val repository: FoodRepository,
    private val addFoodToPetUseCase: AddFoodToPetUseCase
) {
    suspend operator fun invoke(food: FoodModel, petId: Int?): Long {
        val foodId = repository.addFood(food)
        if (petId != null) {
            addFoodToPetUseCase(petId, foodId.toInt())
        }
        return foodId
    }
}
