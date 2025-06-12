package com.maverickapps.tailyCare.petsFeature.domain.otherTasks.useCase

import com.maverickapps.tailyCare.petsFeature.domain.objectTasks.pet.model.PetModel
import com.maverickapps.tailyCare.petsFeature.utils.CaloriesCalculatorCat
import com.maverickapps.tailyCare.petsFeature.utils.CaloriesCalculatorDog
import javax.inject.Inject

class CalculateCaloriesUseCase @Inject constructor(
    private val  calculatorDog: CaloriesCalculatorDog,
    private val  calculatorCat: CaloriesCalculatorCat
){
    operator fun invoke(pet: PetModel): Double {
        return when(pet.animal){
            "dog" -> calculatorDog.calculateCalories(pet)
            "cat" -> calculatorCat.calculateCalories(pet)
            else -> 0.0
        }
    }
}