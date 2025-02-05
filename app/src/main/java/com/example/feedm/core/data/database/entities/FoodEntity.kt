package com.example.feedm.core.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.feedm.petsFeature.domain.model.FoodModel

@Entity(tableName = "food_table")
data class FoodEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "food_id") val foodId : Int = 0,
    @ColumnInfo(name = "food_name") val foodName : String,
    @ColumnInfo(name = "brand") val brand : String,
    @ColumnInfo(name = "food_weight") val foodWeight: Float?,
    @ColumnInfo(name = "calories") val calories: Float
)

fun FoodModel.toDatabase() = FoodEntity(foodId = foodId, foodName = foodName, brand = brand,
    foodWeight = foodWeight, calories = calories)