package com.example.feedm.mealsFeature.domain

import com.example.feedm.mealsFeature.data.MealsRepository
import javax.inject.Inject

class GetMeals @Inject constructor(private val mealsRepository: MealsRepository) { 
    suspend operator fun invoke(petId: Int) = mealsRepository.getMealsByPetId(petId)
}