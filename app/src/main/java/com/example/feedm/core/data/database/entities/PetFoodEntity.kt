package com.example.feedm.core.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "pet_food_table",
    primaryKeys = ["pet_id", "food_id"],
    foreignKeys = [
        ForeignKey(entity = PetEntity::class,
            parentColumns = ["pet_id"],
            childColumns = ["pet_id"],
            onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = FoodEntity::class,
            parentColumns = ["food_id"],
            childColumns = ["food_id"],
            onDelete = ForeignKey.CASCADE)
    ]
)
data class PetFoodEntity(
    @ColumnInfo(name = "pet_id") val petId: Int,
    @ColumnInfo(name = "food_id") val foodId: Int
)