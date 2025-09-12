package com.maverickapps.nutripet.core.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.maverickapps.nutripet.core.data.room.entities.MealEntity

@Dao
interface MealDao {

//Full List

    @Query("SELECT * FROM meal_table")
    suspend fun getAllMeals(): List<MealEntity>

    @Query("SELECT * FROM meal_table WHERE pet_id = :petId")
    suspend fun getMealsByPetId(petId: Int): List<MealEntity>

    @Query("DELETE FROM meal_table")
    suspend fun clearAllMeals()

    @Query("DELETE FROM meal_table WHERE is_daily_meal = 0")
    suspend fun clearNotDailyMeals()

    @Query("SELECT * FROM meal_table WHERE is_daily_meal = 1")
    suspend fun getDailyMeals(): List<MealEntity>


//Individual elements

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMealForAPet(meal: MealEntity): Long

    @Query("SELECT * FROM meal_table WHERE meal_id = :mealId")
    suspend fun getMealById(mealId: Int): MealEntity

    @Query("DELETE FROM meal_table WHERE meal_id == :mealId")
    suspend fun deleteMealForAPet(mealId: Int)

    @Update
    suspend fun editMeal(meal: MealEntity)
}