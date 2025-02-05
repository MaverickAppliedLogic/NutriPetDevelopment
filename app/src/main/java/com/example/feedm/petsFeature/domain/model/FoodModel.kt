package com.example.feedm.petsFeature.domain.model

import com.example.feedm.core.data.database.entities.FoodEntity

data class FoodModel(
    val foodId: Int = 0,
    val foodName: String,
    val brand: String = "noBrand",
    val foodWeight: Float?,
    val calories: Float
)

fun FoodEntity.toDomain() = FoodModel(foodId, foodName, brand, foodWeight, calories)