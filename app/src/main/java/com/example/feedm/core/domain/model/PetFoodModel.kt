package com.example.feedm.core.domain.model

import com.example.feedm.core.data.database.entities.PetFoodEntity

data class PetFoodModel (
    val petId: Int,
    val foodId: Int
)

fun PetFoodEntity.toDomain()= PetFoodModel(petId, foodId)