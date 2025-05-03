package com.example.feedm.petsFeature.domain.objectTasks.meal.useCase

import com.example.feedm.petsFeature.data.MealsRepository
import com.example.feedm.petsFeature.domain.objectTasks.meal.model.MealModel
import javax.inject.Inject

class AddMealUseCase @Inject constructor(private val mealsRepository: MealsRepository) {

    suspend operator fun invoke(mealModel: MealModel){
            mealsRepository.addMealForAPet(mealModel)
    }
}