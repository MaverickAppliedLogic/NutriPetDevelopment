package com.maverickapps.nutripet.petsFeature.domain.objectTasks.meal.useCase

import com.maverickapps.nutripet.petsFeature.data.MealsRepository
import com.maverickapps.nutripet.petsFeature.domain.objectTasks.meal.model.MealModel
import javax.inject.Inject

class AddMealUseCase @Inject constructor(private val mealsRepository: MealsRepository) {

    suspend operator fun invoke(mealModel: MealModel){
            mealsRepository.addMealForAPet(mealModel)
    }
}