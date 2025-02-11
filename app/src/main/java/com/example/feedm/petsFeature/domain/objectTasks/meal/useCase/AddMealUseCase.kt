package com.example.feedm.petsFeature.domain.objectTasks.meal.useCase

import com.example.feedm.petsFeature.domain.objectTasks.meal.model.MealModel
import com.example.feedm.petsFeature.data.MealsRepository
import com.example.feedm.petsFeature.utils.MealModelBuilder
import javax.inject.Inject

class AddMealUseCase @Inject constructor(
    private val mealsRepository: MealsRepository,
    private val mealModelBuilder: MealModelBuilder) {
    suspend operator fun invoke(mealModel: MealModel?,
                                petId: Int?,
                                ration: Float?,
                                hour: Int?,
                                min: Int?,
                                mealCalories: Double?){
        val mealModelBuilt = mealModel ?: mealModelBuilder(petId!!, ration!!, hour!!, min!!, mealCalories!!)
        mealsRepository.addMealForAPet(mealModelBuilt)
    }
}