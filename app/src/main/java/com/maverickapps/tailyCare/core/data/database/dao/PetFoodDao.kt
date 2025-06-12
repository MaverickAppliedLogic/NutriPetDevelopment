package com.maverickapps.tailyCare.core.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.maverickapps.tailyCare.core.data.database.entities.PetFoodEntity

@Dao
interface PetFoodDao {
    @Query("SELECT food_id FROM pet_food_table WHERE pet_id = :petId")
    suspend fun getFoodsIdByPetId(petId: Int): List<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFoodToPet(petFood: PetFoodEntity)
}