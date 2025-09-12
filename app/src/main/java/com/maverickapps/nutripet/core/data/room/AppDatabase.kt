package com.maverickapps.nutripet.core.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.maverickapps.nutripet.core.data.room.dao.FoodDao
import com.maverickapps.nutripet.core.data.room.dao.MealDao
import com.maverickapps.nutripet.core.data.room.dao.PetDao
import com.maverickapps.nutripet.core.data.room.dao.PetFoodDao
import com.maverickapps.nutripet.core.data.room.entities.FoodEntity
import com.maverickapps.nutripet.core.data.room.entities.MealEntity
import com.maverickapps.nutripet.core.data.room.entities.PetEntity
import com.maverickapps.nutripet.core.data.room.entities.PetFoodEntity

@Database(entities = [PetEntity::class, MealEntity::class, FoodEntity::class, PetFoodEntity::class],
    version = 13)

abstract class AppDatabase: RoomDatabase() {

    abstract fun getPetDao(): PetDao

    abstract fun getMealDao(): MealDao

    abstract fun getFoodDao(): FoodDao

    abstract fun getPetFoodDao(): PetFoodDao
}

