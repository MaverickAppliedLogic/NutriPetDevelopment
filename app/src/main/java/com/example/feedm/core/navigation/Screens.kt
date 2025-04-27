package com.example.feedm.core.navigation

import kotlinx.serialization.Serializable

@Serializable
object DashBoardScreen

@Serializable
object AddPetScreen

@Serializable
data class AddMealScreen(val petId: Int? = null)

@Serializable
data class AddFoodScreen(val origin: String? = null)

@Serializable
data class FoodListScreen(val origin: String, val petId: Int? = null)

@Serializable
data class EditPetScreen(val petId: Int)