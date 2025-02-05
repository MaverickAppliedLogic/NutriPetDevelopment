package com.example.feedm.petsFeature.utils

import android.icu.util.Calendar
import com.example.feedm.petsFeature.domain.model.MealModel
import javax.inject.Inject


class MealModelBuilder @Inject constructor(){

    operator fun invoke(petId: Int, ration: Float, hour: Int, min: Int, mealCalories: Double):
            MealModel {

        return MealModel(
            petId = petId,
            mealTime = timeToLong(hour, min),
            ration = ration,
            mealCalories =  mealCalories)
    }

    private fun timeToLong(hour: Int,min: Int): Long{
        val time = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, min)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        return time.timeInMillis
    }
}