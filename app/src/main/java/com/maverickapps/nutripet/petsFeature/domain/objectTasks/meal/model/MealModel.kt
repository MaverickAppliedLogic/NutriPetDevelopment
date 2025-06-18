package com.maverickapps.nutripet.petsFeature.domain.objectTasks.meal.model

import com.maverickapps.nutripet.core.data.database.entities.MealEntity

data class MealModel(
    val mealId : Int = 0,
    val petId: Int,
    val foodId: Int?= 0,
    val mealTime: Long,
    val ration: Float,
    val mealState: Int = 1,
    val mealCalories: Double = 0.0,
    val isDailyMeal: Boolean = false
)

fun MealEntity.toDomain() =
    MealModel(mealId = mealId,
        petId = petId,
        foodId = foodId,
        mealTime = mealTime,
        ration = ration,
        mealState = mealState,
        mealCalories = mealCalories,
        isDailyMeal = isDailyMeal)