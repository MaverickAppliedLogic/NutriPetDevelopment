package com.example.feedm.petsFeature.domain.objectTasks.pet.model

import com.example.feedm.core.data.database.entities.PetEntity


data class PetModel(
    var petId: Int = 0,
    var animal: String,
    var petName: String,
    var age: Float,
    var petWeight: Float,
    var genre: String?,
    var sterilized: Boolean,
    var activity: String?,
    var goal: String,
    var allergies: String?)




fun PetEntity.toDomain()=
    PetModel(petId,animal, petName, age, petWeight, genre, sterilized, activity, goal, allergies)

