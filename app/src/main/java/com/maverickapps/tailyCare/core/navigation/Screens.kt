package com.maverickapps.tailyCare.core.navigation

import kotlinx.serialization.Serializable

@Serializable
data class DashBoardScreen(val shouldRefresh: Boolean?)

@Serializable
data class RegisterPet(val petId: Int?)

@Serializable
data class AddMeal(val petId: Int? = null, val foodId: Int? = null, val mealId: Int? = null)

@Serializable
data class AddFood(val origin: String, val petId: Int? = null)

@Serializable
data class FoodList(val origin: String, val petId: Int? = null)
