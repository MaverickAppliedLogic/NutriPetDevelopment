package com.maverickapps.nutripet.features.pets.utils.caloriesCalculator

import com.maverickapps.nutripet.features.pets.domain.objectTasks.pet.model.PetModel
import kotlin.math.pow

interface CaloriesCalculator {

    fun calculateCalories(pet: PetModel): Double{
        return calculateBaseRequirements(pet) * calculateVariants(pet)
    }

    fun calculateBaseRequirements(pet: PetModel): Double

    fun calculateVariants(pet: PetModel): Double

}


