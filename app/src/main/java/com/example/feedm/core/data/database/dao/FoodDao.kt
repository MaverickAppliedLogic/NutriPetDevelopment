package com.example.feedm.core.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.feedm.core.data.database.entities.FoodEntity

@Dao
interface FoodDao {

//FullList

    @Query("SELECT * FROM food_table")
    suspend fun getAllFoods(): List<FoodEntity>

//Individual elements

    @Query("SELECT * FROM food_table WHERE food_id = :foodId ")
    suspend fun getFoodById(foodId: Int): FoodEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFood(foodEntity: FoodEntity) : Long

    @Query("DELETE FROM food_table WHERE food_id = :foodId")
    suspend fun deleteFood(foodId: Int)
}