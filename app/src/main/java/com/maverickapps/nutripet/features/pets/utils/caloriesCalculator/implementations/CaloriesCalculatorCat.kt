package com.maverickapps.nutripet.features.pets.utils.caloriesCalculator.implementations

import com.maverickapps.nutripet.features.pets.domain.objectTasks.pet.model.PetModel
import com.maverickapps.nutripet.features.pets.utils.caloriesCalculator.CaloriesCalculator

class CaloriesCalculatorCat : CaloriesCalculator {

    override fun calculateBaseRequirements(pet: PetModel): Double {
        val weightDouble = pet.petWeight.toDouble()
        val ageFactor = if(pet.age < 1.0f){ 60 }
        else if (pet.age > 7.0f){ 50 }
        else{ 55 }
        return weightDouble * ageFactor
    }

    override fun calculateVariants(pet: PetModel): Double {

        var variants = 1.0

        variants += when(pet.activity){
            "Alta" -> 0.15
            "Media" -> 0.05
            "Baja"  -> 0.0
            else -> 0.1
        }
        when(pet.goal){
            "Perdida de peso" -> variants -= 0.15
            "Mantenimiento" -> variants -= 0.0
            "Aumento de peso" -> variants += 0.15
        }
        if(pet.sterilized){
            variants -= 0.10
        }

        return variants
    }
}