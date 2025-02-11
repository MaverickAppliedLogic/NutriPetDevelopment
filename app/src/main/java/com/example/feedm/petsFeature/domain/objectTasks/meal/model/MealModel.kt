package com.example.feedm.petsFeature.domain.objectTasks.meal.model

import com.example.feedm.core.data.database.entities.MealEntity

data class MealModel(
    var mealId : Int = 0,
    var petId: Int,
    var mealTime: Long,
    var ration: Float,
    var mealCalories: Double = 0.0
)

fun MealEntity.toDomain() =
    MealModel(mealId = mealId,
        petId = petId,
        mealTime = mealTime,
        ration = ration,
        mealCalories = mealCalories)