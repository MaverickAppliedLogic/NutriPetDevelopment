package com.maverickapps.tailyCare.core.navigation

import kotlinx.serialization.Serializable

@Serializable
data class DashBoardScreen(val shouldRefresh: Boolean?)

@Serializable
object RegisterPet

@Serializable
object AddMeal

@Serializable
data class AddFood(val origin: String)

@Serializable
data class FoodList(val origin: String)
