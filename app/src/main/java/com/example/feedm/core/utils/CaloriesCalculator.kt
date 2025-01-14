package com.example.feedm.core.utils

import com.example.feedm.core.domain.model.PetModel
import kotlin.math.pow

interface CaloriesCalculator {

    fun calculateCalories(pet: PetModel): Double{
        return calculateBaseRequirements(pet) * calculateVariants(pet)
    }

    fun calculateBaseRequirements(pet: PetModel): Double

    fun calculateVariants(pet: PetModel): Double

}

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