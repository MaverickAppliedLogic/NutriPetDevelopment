package com.maverickapps.nutripet.features.pets.utils.caloriesCalculator.implementations

import com.maverickapps.nutripet.features.pets.domain.objectTasks.pet.model.PetModel
import com.maverickapps.nutripet.features.pets.utils.caloriesCalculator.CaloriesCalculator
import kotlin.math.pow

class CaloriesCalculatorDog : CaloriesCalculator {

    override fun calculateBaseRequirements(pet: PetModel): Double {
        val weightDouble = pet.petWeight.toDouble()
        val ageFactor = if(pet.age < 1.0f){ 2.2 }
        else if (pet.age > 7.0f){ 1.3 }
        else{ 1.5 }
        return 70 * weightDouble.pow(0.75) * ageFactor
    }

    override fun calculateVariants(pet: PetModel): Double {

        var variants = 1.0

        variants += when(pet.activity){
            "Alta" -> 0.2
            "Media" -> 0.1
            "Baja"  -> 0.00
            else -> 0.00
        }
        when(pet.goal){
            "Perdida de peso" -> variants -= 0.1
            "Mantenimiento" -> variants += 0.0
            "Aumento de peso" -> variants += 0.1
        }
        if(pet.sterilized){
            variants -= 0.15
        }

        return variants
    }

}
