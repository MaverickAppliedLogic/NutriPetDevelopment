package com.example.feedm.core.domain.model

import com.example.feedm.core.data.database.entities.MealEntity

data class MealModel(
    var mealId : Int = 0,
    var petId: Int,
    var mealTime: Long,
    var ration: Float
)

fun MealEntity.toDomain() =
    MealModel(mealId = mealId, petId = petId, mealTime = mealTime, ration = ration)