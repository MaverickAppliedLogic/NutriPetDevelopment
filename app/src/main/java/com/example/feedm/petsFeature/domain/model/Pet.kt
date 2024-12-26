package com.example.feedm.petsFeature.domain.model

import com.example.feedm.core.database.entities.PetEntity
import com.example.feedm.petsFeature.data.local.PetModel


data class Pet(
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




fun PetModel.toDomain()=
    Pet(animal=animal, petName=name, age=age, petWeight=petWeight, genre=genre, sterilized=sterilized,
        activity=activity, goal=goal, allergies=allergies, foodId = foodId)

fun PetEntity.toDomain()=
    Pet(petId,foodId,animal, petName, age, petWeight, genre, sterilized, activity, goal, allergies)

