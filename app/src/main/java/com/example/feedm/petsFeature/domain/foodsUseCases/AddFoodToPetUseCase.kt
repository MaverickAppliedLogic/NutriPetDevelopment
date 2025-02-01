package com.example.feedm.petsFeature.domain.foodsUseCases

import com.example.feedm.core.domain.model.PetFoodModel
import com.example.feedm.petsFeature.data.PetFoodRepository
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