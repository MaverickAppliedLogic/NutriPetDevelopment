package com.example.feedm.petsFeature.domain.foodsUseCases

import com.example.feedm.core.domain.model.FoodModel
import com.example.feedm.petsFeature.data.FoodRepository
import com.example.feedm.petsFeature.data.PetFoodRepository
import jakarta.inject.Inject

class GetFoodsByPetIdUseCase @Inject constructor(
    private val petFoodRepository: PetFoodRepository,
    private val foodRepository: FoodRepository
) {
    suspend operator fun invoke(petId: Int): List<FoodModel> {
        val foodsList = emptyList<FoodModel>().toMutableList()
        val foodsId = petFoodRepository.getFoodsIdByPetId(petId)
        foodsId.forEach {
            foodsList.add(foodRepository.getFoodById(it))
        }
        return foodsList
    }
}