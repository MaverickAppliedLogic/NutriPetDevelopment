package com.maverickapps.nutripet.features.pets.data.repositories

import com.maverickapps.nutripet.core.data.room.dao.FoodDao
import com.maverickapps.nutripet.core.data.room.entities.toDatabase
import com.maverickapps.nutripet.features.pets.domain.objectTasks.food.model.FoodModel
import com.maverickapps.nutripet.features.pets.domain.objectTasks.food.model.toDomain
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