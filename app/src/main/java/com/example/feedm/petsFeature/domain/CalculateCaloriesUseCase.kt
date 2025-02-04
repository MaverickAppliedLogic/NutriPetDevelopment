package com.example.feedm.petsFeature.domain

import com.example.feedm.core.domain.model.PetModel
import com.example.feedm.core.utils.CaloriesCalculatorCat
import com.example.feedm.core.utils.CaloriesCalculatorDog
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