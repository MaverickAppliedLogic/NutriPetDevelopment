package com.example.feedm.core.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.feedm.core.data.database.dao.FoodDao
import com.example.feedm.core.data.database.dao.MealDao
import com.example.feedm.core.data.database.dao.PetDao
import com.example.feedm.core.data.database.entities.FoodEntity
import com.example.feedm.core.data.database.entities.MealEntity
import com.example.feedm.core.data.database.entities.PetEntity
import dagger.Provides

@Database(entities = [PetEntity::class, MealEntity::class, FoodEntity::class], version = 7)

abstract class AppDatabase: RoomDatabase() {

    abstract fun getPetDao(): PetDao

    abstract fun getMealDao(): MealDao

    abstract fun getFoodDao(): FoodDao
}

