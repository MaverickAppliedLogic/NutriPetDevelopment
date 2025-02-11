package com.example.feedm.petsFeature.data

import com.example.feedm.core.data.database.dao.MealDao
import com.example.feedm.core.data.database.entities.toDatabase
import com.example.feedm.petsFeature.domain.objectTasks.meal.model.MealModel
import com.example.feedm.petsFeature.domain.objectTasks.meal.model.toDomain
import javax.inject.Inject

class MealsRepository @Inject constructor(private val mealsDao: MealDao) {

    suspend fun getMealsByPetId(petId: Int): List<MealModel> {
       return mealsDao.getMealsByPetId(petId).map { it.toDomain() }
    }

    suspend fun clearAllMeals() {
        mealsDao.clearAllMeals()
    }

    suspend fun addMealForAPet(meal: MealModel) {
        mealsDao.addMealForAPet(meal.toDatabase())
    }

    suspend fun deleteMealForAPet(mealId: List<Int>) {
        mealsDao.deleteMealForAPet(mealId)
    }
}