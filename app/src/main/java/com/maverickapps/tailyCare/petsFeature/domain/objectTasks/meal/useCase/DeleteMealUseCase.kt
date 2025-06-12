package com.maverickapps.tailyCare.petsFeature.domain.objectTasks.meal.useCase

import com.maverickapps.tailyCare.petsFeature.data.MealsRepository
import javax.inject.Inject

class DeleteMealUseCase @Inject constructor(private val mealsRepository: MealsRepository){
    suspend operator fun invoke(mealId: List<Int>){
        mealsRepository.deleteMealForAPet(mealId)
    }
}