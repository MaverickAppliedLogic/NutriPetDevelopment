package com.maverickapps.nutripet.features.pets.domain.objectTasks.food.useCase

import com.maverickapps.nutripet.features.pets.data.FoodRepository
import com.maverickapps.nutripet.features.pets.domain.objectTasks.food.model.FoodModel
import javax.inject.Inject

class GetAllFoodsUseCase @Inject constructor(private val repository: FoodRepository) {
    suspend operator fun invoke(): List<FoodModel> = repository.getAllFoods()
}