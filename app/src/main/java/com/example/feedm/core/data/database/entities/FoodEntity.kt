package com.example.feedm.core.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food_table")
data class FoodEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "food_id") val foodId : Int = 0,
    @ColumnInfo(name = "food_name") val foodName : String,
    @ColumnInfo(name = "brand") val brand : String,
    @ColumnInfo(name = "food_weight") val foodWeight: Float?,
    @ColumnInfo(name = "calories") val calories: Float
)
