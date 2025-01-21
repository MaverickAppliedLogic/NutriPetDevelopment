package com.example.feedm.core.domain.model

data class FoodModel(
    val foodId: Int = 0,
    val foodName: String,
    val brand: String = "noBrand",
    val foodWeight: Float?,
    val calories: Float
)
