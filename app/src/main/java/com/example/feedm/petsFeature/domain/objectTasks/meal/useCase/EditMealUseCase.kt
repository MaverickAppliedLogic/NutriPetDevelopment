package com.example.feedm.petsFeature.domain.objectTasks.meal.useCase

import com.example.feedm.petsFeature.data.MealsRepository
import com.example.feedm.petsFeature.domain.objectTasks.meal.model.MealModel
import javax.inject.Inject

class EditMealUseCase @Inject constructor(private val mealRepository: MealsRepository) {

    suspend operator fun invoke(meal: MealModel) = mealRepository.editMeal(meal)
}