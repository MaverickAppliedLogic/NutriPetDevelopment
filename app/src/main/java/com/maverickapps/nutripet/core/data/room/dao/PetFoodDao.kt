package com.maverickapps.nutripet.core.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.maverickapps.nutripet.core.data.room.entities.PetFoodEntity

@Dao
interface PetFoodDao {
    @Query("SELECT food_id FROM pet_food_table WHERE pet_id = :petId")
    suspend fun getFoodsIdByPetId(petId: Int): List<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFoodToPet(petFood: PetFoodEntity)
}