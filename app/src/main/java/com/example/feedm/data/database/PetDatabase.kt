package com.example.feedm.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.feedm.data.database.dao.PetDao
import com.example.feedm.data.database.entities.PetEntity

@Database(entities = [PetEntity::class], version = 2)
abstract class PetDatabase: RoomDatabase() {

    abstract fun getPetDao(): PetDao
}