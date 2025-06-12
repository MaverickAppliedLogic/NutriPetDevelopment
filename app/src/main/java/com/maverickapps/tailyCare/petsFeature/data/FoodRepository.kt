package com.maverickapps.tailyCare.petsFeature.data

import com.maverickapps.tailyCare.core.data.database.dao.FoodDao
import com.maverickapps.tailyCare.core.data.database.entities.toDatabase
import com.maverickapps.tailyCare.petsFeature.domain.objectTasks.food.model.FoodModel
import com.maverickapps.tailyCare.petsFeature.domain.objectTasks.food.model.toDomain
import javax.inject.Inject

class FoodRepository @Inject constructor(
    private val foodDao: FoodDao,
) {

    suspend fun getAllFoods(): List<FoodModel> {
        return foodDao.getAllFoods().map { it.toDomain() }
    }

    suspend fun getFoodById(foodId: Int): FoodModel {
        return foodDao.getFoodById(foodId).toDomain()
    }

    suspend fun addFood(food: FoodModel): Long {
        return foodDao.addFood(food.toDatabase())
    }


    suspend fun deleteFood(food: FoodModel) {
        foodDao.deleteFood(food.foodId)
    }
}