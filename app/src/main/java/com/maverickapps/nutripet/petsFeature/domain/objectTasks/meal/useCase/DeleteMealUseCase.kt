package com.maverickapps.nutripet.petsFeature.domain.objectTasks.meal.useCase

import com.maverickapps.nutripet.petsFeature.data.MealsRepository
import javax.inject.Inject

class DeleteMealUseCase @Inject constructor(private val mealsRepository: MealsRepository){
    suspend operator fun invoke(mealId: List<Int>){
        mealsRepository.deleteMealForAPet(mealId)
    }
}