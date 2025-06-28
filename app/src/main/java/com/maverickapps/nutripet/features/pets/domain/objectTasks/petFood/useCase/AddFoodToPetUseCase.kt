package com.maverickapps.nutripet.features.pets.domain.objectTasks.petFood.useCase

import com.maverickapps.nutripet.features.pets.data.PetFoodRepository
import com.maverickapps.nutripet.features.pets.domain.objectTasks.petFood.model.PetFoodModel
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