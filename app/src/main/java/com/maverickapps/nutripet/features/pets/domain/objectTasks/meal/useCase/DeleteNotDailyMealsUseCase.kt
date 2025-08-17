package com.maverickapps.nutripet.features.pets.domain.objectTasks.meal.useCase

import javax.inject.Inject

class DeleteNotDailyMealsUseCase @Inject constructor(
    private val deleteMealUseCase: DeleteMealUseCase,
    private val getAllMealsUseCase: GetAllMealsUseCase
){
    suspend operator fun invoke(){
        val notDailyMeals = getAllMealsUseCase().filter { !it.isDailyMeal }
        for(meal in notDailyMeals){
            deleteMealUseCase(meal)
        }
    }
}