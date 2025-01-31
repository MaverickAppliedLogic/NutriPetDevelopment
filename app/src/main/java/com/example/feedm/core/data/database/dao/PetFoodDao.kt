package com.example.feedm.core.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.feedm.core.data.database.entities.PetFoodEntity

@Dao
interface PetFoodDao {
    @Query("SELECT * FROM pet_food_table WHERE pet_id = :petId")
    fun getFoodsByPetId(petId: Int): List<PetFoodEntity>
}