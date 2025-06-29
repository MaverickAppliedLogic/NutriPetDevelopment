package com.maverickapps.nutripet.features.pets.domain.objectTasks.meal.useCase

import com.maverickapps.nutripet.features.pets.data.repositories.MealsRepository
import javax.inject.Inject

class DeleteMealUseCase @Inject constructor(private val mealsRepository: MealsRepository){
    suspend operator fun invoke(mealId: List<Int>){
        mealsRepository.deleteMealForAPet(mealId)
    }
}