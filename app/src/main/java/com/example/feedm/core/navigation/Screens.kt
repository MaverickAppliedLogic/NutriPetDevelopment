package com.example.feedm.core.navigation

import kotlinx.serialization.Serializable

@Serializable
object DashBoardScreen

@Serializable
object AddPetScreen

@Serializable
object AddMealScreen

@Serializable
data class AddFoodScreen(val origin: String? = null)

@Serializable
data class FoodListScreen(val origin: String)

@Serializable
object EditPetScreen