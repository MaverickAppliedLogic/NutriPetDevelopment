package com.maverickapps.nutripet.features.pets.data

import com.maverickapps.nutripet.core.data.database.dao.MealDao
import com.maverickapps.nutripet.core.data.database.entities.toDatabase
import com.maverickapps.nutripet.features.pets.domain.objectTasks.meal.model.MealModel
import com.maverickapps.nutripet.features.pets.domain.objectTasks.meal.model.toDomain
import javax.inject.Inject

class MealsRepository @Inject constructor(private val mealsDao: MealDao) {

    suspend fun getMealsByPetId(petId: Int): List<MealModel> {
       return mealsDao.getMealsByPetId(petId).map { it.toDomain() }
    }

    suspend fun getMealById(mealId: Int): MealModel {
       return mealsDao.getMealById(mealId).toDomain()
    }

    suspend fun clearAllMeals() {
        mealsDao.clearAllMeals()
    }

    suspend fun getDailyMeals(): List<MealModel> {
        return mealsDao.getDailyMeals().map { it.toDomain() }
    }

    suspend fun clearNotDailyMeals() {
        mealsDao.clearNotDailyMeals()
    }

    suspend fun addMealForAPet(meal: MealModel) {
        mealsDao.addMealForAPet(meal.toDatabase())
    }

    suspend fun editMeal(meal: MealModel) {
        mealsDao.editMeal(meal.toDatabase())
    }

    suspend fun deleteMealForAPet(mealId: List<Int>) {
        mealsDao.deleteMealForAPet(mealId)
    }
}