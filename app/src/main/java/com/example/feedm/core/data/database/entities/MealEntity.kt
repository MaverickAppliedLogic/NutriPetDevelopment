package com.example.feedm.core.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.feedm.core.domain.model.MealModel

@Entity(
    tableName = "meal_table",
    foreignKeys = [
        ForeignKey(
            entity = PetEntity::class,
            parentColumns = ["pet_id"],
            childColumns = ["pet_id"],
            onDelete = ForeignKey.NO_ACTION
        ),
        ForeignKey(
            entity = PetEntity::class,
            parentColumns = ["food_id"],
            childColumns = ["food_id"],
            onDelete = ForeignKey.NO_ACTION
        )],
    indices = [
        Index(value = ["pet_id"]),
        Index(value = ["food_id"])
    ]
)
data class MealEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "meal_id") val mealId: Int = 0,
    @ColumnInfo(name = "pet_id") val petId: Int,
    @ColumnInfo(name = "food_id") val foodId: Int?,
    @ColumnInfo(name = "meal_time") val mealTime: Long,
    @ColumnInfo(name = "ration") val ration: Float
)

fun MealModel.toDatabase() =
    MealEntity(mealId = mealId, petId = petId, foodId = foodId, mealTime = mealTime, ration = ration)
