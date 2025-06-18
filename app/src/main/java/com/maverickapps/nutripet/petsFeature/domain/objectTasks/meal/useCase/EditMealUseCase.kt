package com.maverickapps.nutripet.petsFeature.domain.objectTasks.meal.useCase

import com.maverickapps.nutripet.petsFeature.data.MealsRepository
import com.maverickapps.nutripet.petsFeature.domain.objectTasks.meal.model.MealModel
import javax.inject.Inject

class EditMealUseCase @Inject constructor(private val mealRepository: MealsRepository) {

    suspend operator fun invoke(meal: MealModel) = mealRepository.editMeal(meal)
}