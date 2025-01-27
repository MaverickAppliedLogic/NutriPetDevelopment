package com.example.feedm.petsFeature.domain.mealsUseCases

import com.example.feedm.petsFeature.data.MealsRepository
import javax.inject.Inject

class GetMeals @Inject constructor(private val mealsRepository: MealsRepository) {
    suspend operator fun invoke(petId: Int) = mealsRepository.getMealsByPetId(petId)
}