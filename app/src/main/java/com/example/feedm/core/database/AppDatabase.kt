package com.example.feedm.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.feedm.core.database.dao.FoodDao
import com.example.feedm.core.database.dao.MealDao
import com.example.feedm.core.database.dao.PetDao
import com.example.feedm.core.database.entities.FoodEntity
import com.example.feedm.core.database.entities.MealEntity
import com.example.feedm.core.database.entities.PetEntity

@Database(entities = [PetEntity::class,MealEntity::class,FoodEntity::class], version = 6)

abstract class AppDatabase: RoomDatabase() {

    abstract fun getPetDao(): PetDao

    abstract fun getMealDao(): MealDao

    abstract fun getFoodDao(): FoodDao
}

