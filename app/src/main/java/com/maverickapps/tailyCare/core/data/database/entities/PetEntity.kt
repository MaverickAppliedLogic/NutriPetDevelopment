package com.maverickapps.tailyCare.core.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.maverickapps.tailyCare.petsFeature.domain.objectTasks.pet.model.PetModel

@Entity(tableName = "pet_table")
data class PetEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "pet_id")val petId: Int = 0,
    @ColumnInfo(name = "animal")val animal: String,
    @ColumnInfo(name = "pet_name")val petName: String,
    @ColumnInfo(name = "age")val age: Float,
    @ColumnInfo(name = "pet_weight")val petWeight: Float,
    @ColumnInfo(name = "genre")val genre: String?,
    @ColumnInfo(name = "sterilized")val sterilized: Boolean,
    @ColumnInfo(name = "activity")val activity: String?,
    @ColumnInfo(name = "goal")val goal: String,
    @ColumnInfo(name = "allergies")val allergies: String?)


fun PetModel.toDataBase() =
    PetEntity(
        petId = petId,
        animal =  animal,
        petName =  petName,
        age = age,
        petWeight =  petWeight,
        genre = genre,
        sterilized = sterilized,
        activity =  activity,
        goal =  goal,
        allergies =  allergies)