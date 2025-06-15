package com.maverickapps.nutripet.core.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.maverickapps.nutripet.core.data.database.dao.FoodDao
import com.maverickapps.nutripet.core.data.database.dao.MealDao
import com.maverickapps.nutripet.core.data.database.dao.PetDao
import com.maverickapps.nutripet.core.data.database.dao.PetFoodDao
import com.maverickapps.nutripet.core.data.database.entities.FoodEntity
import com.maverickapps.nutripet.core.data.database.entities.MealEntity
import com.maverickapps.nutripet.core.data.database.entities.PetEntity
import com.maverickapps.nutripet.core.data.database.entities.PetFoodEntity

@Database(entities = [PetEntity::class, MealEntity::class, FoodEntity::class, PetFoodEntity::class],
    version = 13)

abstract class AppDatabase: RoomDatabase() {

    abstract fun getPetDao(): PetDao

    abstract fun getMealDao(): MealDao

    abstract fun getFoodDao(): FoodDao

    abstract fun getPetFoodDao(): PetFoodDao
}

