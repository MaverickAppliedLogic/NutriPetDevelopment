package com.example.feedm.petsFeature.data

import com.example.feedm.core.data.database.dao.FoodDao
import com.example.feedm.core.data.database.entities.toDatabase
import com.example.feedm.core.domain.model.FoodModel
import com.example.feedm.core.domain.model.toDomain
import javax.inject.Inject

class FoodRepository @Inject constructor(
    private val foodDao: FoodDao,
) {

    // DataBase

    suspend fun getFoodById(foodId: Int): FoodModel {
        return foodDao.getFoodById(foodId).toDomain()
    }

    suspend fun addFood(food: FoodModel) {
        foodDao.addFood(food.toDatabase())
    }


    suspend fun deleteFood(food: FoodModel) {
        foodDao.deleteFood(food.foodId)
    }
}