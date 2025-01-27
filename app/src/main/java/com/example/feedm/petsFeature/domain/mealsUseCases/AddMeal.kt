package com.example.feedm.petsFeature.domain.mealsUseCases

import com.example.feedm.core.domain.model.MealModel
import com.example.feedm.petsFeature.data.MealsRepository
import javax.inject.Inject

class AddMeal @Inject constructor(private val mealsRepository: MealsRepository) {
    suspend operator fun invoke(mealModel: MealModel){
        mealsRepository.addMealForAPet(mealModel)
    }
}