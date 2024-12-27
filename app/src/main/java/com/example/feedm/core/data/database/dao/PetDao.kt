package com.example.feedm.core.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.feedm.core.data.database.entities.PetEntity
import com.example.feedm.core.domain.model.PetModel

@Dao
interface PetDao {

//Full List
    @Query("SELECT * FROM pet_table ORDER BY pet_id ASC")
    suspend fun getAllPets(): List<PetEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(pets:List<PetEntity>)

    @Query("DELETE FROM pet_table ")
    suspend fun deleteAllPets()

//Individual elements
    @Query("SELECT * FROM pet_table WHERE pet_id = :petId")
    suspend fun getPetById(petId: Int) : PetEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPet(pet: PetEntity)

    @Update
    suspend fun updatePet(pet: PetEntity)

    @Query("DELETE FROM pet_table WHERE pet_id = :petId")
    suspend fun deletePet(petId: Int)
}