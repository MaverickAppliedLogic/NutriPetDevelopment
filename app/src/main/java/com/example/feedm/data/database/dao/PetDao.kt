package com.example.feedm.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.feedm.data.database.entities.PetEntity
import com.example.feedm.domain.model.Pet

@Dao
interface PetDao {

//Full List
    @Query("SELECT * FROM pet_table ORDER BY id ASC")
    suspend fun getAllPets(): List<PetEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(pets:List<PetEntity>)

    @Query("DELETE FROM pet_table ")
    suspend fun deleteAllPets()

//Individual elements
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPet(pet: PetEntity)

    @Update
    suspend fun updatePet(pet: PetEntity)

    @Query("DELETE FROM pet_table WHERE id = :id")
    suspend fun deletePet(id: Int)
}