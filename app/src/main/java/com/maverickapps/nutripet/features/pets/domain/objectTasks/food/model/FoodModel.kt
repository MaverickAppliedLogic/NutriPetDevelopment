package com.maverickapps.nutripet.features.pets.domain.objectTasks.food.model

import com.maverickapps.nutripet.core.data.room.entities.FoodEntity

data class FoodModel(
    val foodId: Int = 0,
    val foodName: String,
    val brand: String = "noBrand",
    val foodWeight: Float?,
    val calories: Float
)

fun FoodEntity.toDomain() = FoodModel(foodId, foodName, brand, foodWeight, calories)