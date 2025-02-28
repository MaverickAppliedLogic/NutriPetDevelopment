package com.example.feedm.petsFeature.utils

import com.example.feedm.petsFeature.domain.objectTasks.meal.model.MealModel
import javax.inject.Inject


class MealModelBuilder @Inject constructor(){

    operator fun invoke(
        petId: Int, foodId: Int, ration: Float, hour: Int, min: Int, mealCalories: Double
    ): MealModel {
        return MealModel(
            petId = petId,
            foodId = foodId,
            mealTime = TimeFormatter().formatIntToMills(hour, min, 0, 0),
            ration = ration,
            mealCalories =  mealCalories * ration / 100)
    }

}