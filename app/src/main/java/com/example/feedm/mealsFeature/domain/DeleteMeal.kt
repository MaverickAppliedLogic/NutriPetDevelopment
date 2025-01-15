package com.example.feedm.mealsFeature.domain

import com.example.feedm.mealsFeature.data.MealsRepository
import javax.inject.Inject

class DeleteMeal @Inject constructor(private val mealsRepository: MealsRepository){
    suspend operator fun invoke(mealId: Int){
        mealsRepository.deleteMealForAPet(mealId)
    }
}