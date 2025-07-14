package com.maverickapps.nutripet.features.pets.domain.otherTasks.useCase

import com.maverickapps.nutripet.features.pets.domain.objectTasks.pet.model.PetModel
import com.maverickapps.nutripet.features.pets.utils.caloriesCalculator.implementations.CaloriesCalculatorCat
import com.maverickapps.nutripet.features.pets.utils.caloriesCalculator.implementations.CaloriesCalculatorDog
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