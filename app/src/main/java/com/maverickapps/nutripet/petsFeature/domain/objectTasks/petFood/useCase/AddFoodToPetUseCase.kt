package com.maverickapps.nutripet.petsFeature.domain.objectTasks.petFood.useCase

import com.maverickapps.nutripet.petsFeature.domain.objectTasks.petFood.model.PetFoodModel
import com.maverickapps.nutripet.petsFeature.data.PetFoodRepository
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