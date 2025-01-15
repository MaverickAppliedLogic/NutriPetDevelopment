package com.example.feedm.mealsFeature.domain

import com.example.feedm.core.domain.model.MealModel
import com.example.feedm.mealsFeature.data.MealsRepository
import javax.inject.Inject

class AddMeal @Inject constructor(private val mealsRepository: MealsRepository) { 
    suspend operator fun invoke(mealModel: MealModel){
        mealsRepository.addMealForAPet(mealModel)
    }
}