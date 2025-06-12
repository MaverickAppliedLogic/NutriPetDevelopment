package com.maverickapps.tailyCare.petsFeature.domain.objectTasks.food.useCase

import com.maverickapps.tailyCare.petsFeature.data.FoodRepository
import com.maverickapps.tailyCare.petsFeature.domain.objectTasks.food.model.FoodModel
import javax.inject.Inject

class GetAllFoodsUseCase @Inject constructor(private val repository: FoodRepository) {
    suspend operator fun invoke(): List<FoodModel> = repository.getAllFoods()
}