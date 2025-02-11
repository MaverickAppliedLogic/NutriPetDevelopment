package com.example.feedm.petsFeature.domain.objectTasks.meal.useCase

import com.example.feedm.core.domain.useCase.CheckDayChangedUseCase
import com.example.feedm.petsFeature.data.MealsRepository
import javax.inject.Inject

class GetMealsUseCase @Inject constructor(
    private val mealsRepository: MealsRepository,
    private val checkDayChangedUseCase: CheckDayChangedUseCase,
    private val updateMealsDayChangedUseCase: UpdateMealsDayChangedUseCase
) {
    suspend operator fun invoke(petId: Int) =
    if(checkDayChangedUseCase()){
        updateMealsDayChangedUseCase()
        mealsRepository.getMealsByPetId(petId)
    }
    else{
        mealsRepository.getMealsByPetId(petId)
    }
}
