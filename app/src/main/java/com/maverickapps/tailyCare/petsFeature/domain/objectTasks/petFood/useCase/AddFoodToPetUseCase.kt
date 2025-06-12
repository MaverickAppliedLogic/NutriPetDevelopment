package com.maverickapps.tailyCare.petsFeature.domain.objectTasks.petFood.useCase

import com.maverickapps.tailyCare.petsFeature.domain.objectTasks.petFood.model.PetFoodModel
import com.maverickapps.tailyCare.petsFeature.data.PetFoodRepository
import jakarta.inject.Inject

class AddFoodToPetUseCase @Inject constructor(
    private val petFoodRepository: PetFoodRepository
)
{
    suspend operator fun invoke(petId: Int, foodId: Int) {
        val petFood = PetFoodModel(petId = petId, foodId = foodId)
        petFoodRepository.addFoodToPet(petFood)
    }
}