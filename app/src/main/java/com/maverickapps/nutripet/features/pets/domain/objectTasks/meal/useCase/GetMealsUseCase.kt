package com.maverickapps.nutripet.features.pets.domain.objectTasks.meal.useCase

import com.maverickapps.nutripet.features.events.domain.useCase.CheckDayChangedUseCase
import com.maverickapps.nutripet.features.pets.data.repositories.MealsRepository
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
