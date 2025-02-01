package com.example.feedm.core.data.database.dao

import androidx.room.Dao
import androidx.room.Query

@Dao
interface PetFoodDao {
    @Query("SELECT food_id FROM pet_food_table WHERE pet_id = :petId")
    suspend fun getFoodsIdByPetId(petId: Int): List<Int>
}