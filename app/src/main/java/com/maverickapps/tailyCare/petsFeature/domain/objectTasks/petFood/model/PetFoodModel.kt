package com.maverickapps.tailyCare.petsFeature.domain.objectTasks.petFood.model

import com.maverickapps.tailyCare.core.data.database.entities.PetFoodEntity

data class PetFoodModel (
    val petId: Int,
    val foodId: Int
)

fun PetFoodEntity.toDomain()= PetFoodModel(petId, foodId)