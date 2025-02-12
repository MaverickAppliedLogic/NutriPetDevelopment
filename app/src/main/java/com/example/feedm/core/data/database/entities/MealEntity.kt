package com.example.feedm.core.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.feedm.petsFeature.domain.objectTasks.meal.model.MealModel

@Entity(
    tableName = "meal_table",
    foreignKeys = [
        ForeignKey(
            entity = PetEntity::class,
            parentColumns = ["pet_id"],
            childColumns = ["pet_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = FoodEntity::class,
            parentColumns = ["food_id"],
            childColumns = ["food_id"],
            onDelete = ForeignKey.SET_DEFAULT
        )])
data class MealEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "meal_id") val mealId: Int = 0,
    @ColumnInfo(name = "pet_id") val petId: Int,
    @ColumnInfo(name = "food_id") val foodId: Int = 0,
    @ColumnInfo(name = "meal_time") val mealTime: Long,
    @ColumnInfo(name = "ration") val ration: Float,
    @ColumnInfo(name = "meal_calories") val mealCalories: Double
)

fun MealModel.toDatabase() =
    MealEntity(mealId = mealId,
        petId = petId,
        foodId = foodId,
        mealTime = mealTime,
        ration = ration,
        mealCalories = mealCalories)
