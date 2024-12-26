package com.example.feedm.petsFeature.data.local

import com.example.feedm.petsFeature.domain.model.Pet

data class PetModel (
    var animal: String,
    var name: String,
    var age: Float,
    var petWeight: Float,
    var genre: String?,
    var sterilized: Boolean?,
    var activity: String,
    var goal: String,
    var allergies: String?,
    var foodId: Int?

)


fun Pet.toStorage()=
    PetModel(
        animal =  animal,
        name =  petName,
        age = age,
        petWeight =  petWeight,
        genre = genre,
        sterilized = sterilized,
        activity =  activity,
        goal =  goal,
        allergies =  allergies,
        foodId = foodId)


