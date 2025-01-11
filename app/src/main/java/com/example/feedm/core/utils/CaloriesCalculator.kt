package com.example.feedm.core.utils

import com.example.feedm.core.domain.model.PetModel
import kotlin.math.pow

interface CaloriesCalculator {

    fun calculateCalories(pet: PetModel): Double{
        val b = calculateBaseRequirements(pet.petWeight, pet.age)
        val v = calculateVariants(pet.activity, pet.goal, pet.sterilized)
        return b * v
    }

    fun calculateBaseRequirements(weight: Float, age: Float): Double

    fun calculateVariants(activity: String?, goal: String, sterilized: Boolean): Double

}

class CaloriesCalculatorDog : CaloriesCalculator {

    override fun calculateBaseRequirements(weight: Float, age: Float): Double {
        val weightDouble = weight.toDouble()
        val ageFactor = if(age < 1.0f){ 2.2 }
        else if (age > 7.0f){ 1.3 }
        else{ 1.5 }
        return 70 * weightDouble.pow(0.75) * ageFactor
    }

    override fun calculateVariants(activity: String?, goal: String, sterilized: Boolean): Double {

        var variants = 1.0

        variants += when(activity){
            "Alta" -> 0.2
            "Media" -> 0.1
            "Baja"  -> 0.00
            else -> 0.00
        }
        when(goal){
            "Perdida de peso" -> variants -= 0.1
            "Mantenimiento" -> variants += 0.0
            "Aumento de peso" -> variants += 0.1
        }
        if(sterilized){
            variants -= 0.15
        }

        return variants
    }

}