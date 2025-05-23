package com.example.feedm.petsFeature.domain.objectTasks.food.useCase

import com.example.feedm.petsFeature.data.FoodRepository
import com.example.feedm.petsFeature.domain.objectTasks.food.model.FoodModel
import javax.inject.Inject

class GetAllFoodsUseCase @Inject constructor(private val repository: FoodRepository) {
    suspend operator fun invoke(): List<FoodModel> = repository.getAllFoods()
}