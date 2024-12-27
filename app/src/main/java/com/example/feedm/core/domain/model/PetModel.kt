package com.example.feedm.core.domain.model

import com.example.feedm.core.data.database.entities.PetEntity


data class PetModel(
    var petId: Int = 0,
    var foodId: Int?,
    var animal: String,
    var petName: String,
    var age: Float,
    var petWeight: Float,
    var genre: String?,
    var sterilized: Boolean?,
    var activity: String,
    var goal: String,
    var allergies: String?)




fun PetEntity.toDomain()=
    PetModel(petId,foodId,animal, petName, age, petWeight, genre, sterilized, activity, goal, allergies)

