package com.example.feedm.core.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.feedm.petsFeature.domain.model.Pet

@Entity(tableName = "pet_table",
    foreignKeys = [
        ForeignKey(
            entity = FoodEntity::class,
            parentColumns = ["food_id"],
            childColumns = ["food_id"],
            onDelete = ForeignKey.NO_ACTION
        )
    ], indices = [Index(value = ["food_id"],
        unique = true)])
data class PetEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "pet_id")val petId: Int = 0,
    @ColumnInfo(name = "food_id")val foodId: Int?,
    @ColumnInfo(name = "animal")val animal: String,
    @ColumnInfo(name = "pet_name")val petName: String,
    @ColumnInfo(name = "age")val age: Float,
    @ColumnInfo(name = "pet_weight")val petWeight: Float,
    @ColumnInfo(name = "genre")val genre: String?,
    @ColumnInfo(name = "sterilized")val sterilized: Boolean?,
    @ColumnInfo(name = "activity")val activity: String,
    @ColumnInfo(name = "goal")val goal: String,
    @ColumnInfo(name = "allergies")val allergies: String?)


fun Pet.toDataBase() =
    PetEntity(
        petId = petId,
        foodId = foodId,
        animal =  animal,
        petName =  petName,
        age = age,
        petWeight =  petWeight,
        genre = genre,
        sterilized = sterilized,
        activity =  activity,
        goal =  goal,
        allergies =  allergies)