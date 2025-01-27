package com.example.feedm.petsFeature.domain.mealsUseCases

import com.example.feedm.petsFeature.data.MealsRepository
import javax.inject.Inject

class DeleteMeal @Inject constructor(private val mealsRepository: MealsRepository){
    suspend operator fun invoke(mealId: Int){
        mealsRepository.deleteMealForAPet(mealId)
    }
}