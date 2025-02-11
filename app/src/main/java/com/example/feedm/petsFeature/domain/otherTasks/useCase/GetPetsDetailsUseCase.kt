package com.example.feedm.petsFeature.domain.otherTasks.useCase

import com.example.feedm.petsFeature.domain.objectTasks.meal.useCase.GetMealsUseCase
import com.example.feedm.petsFeature.domain.objectTasks.pet.useCase.GetPetByIdUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetPetsDetailsUseCase @Inject constructor(
    private val getPetByIdUseCase: GetPetByIdUseCase,
    private val getMealsUseCase: GetMealsUseCase,
    private val calculateCaloriesUseCase: CalculateCaloriesUseCase
) {

    suspend operator fun invoke(petId: Int) = withContext(Dispatchers.IO){

        val pet = async { getPetByIdUseCase(petId)}
        val meals = async { getMealsUseCase(petId)}
        val calories = async { calculateCaloriesUseCase(pet.await())}

        return@withContext arrayListOf(pet.await(),meals.await(),calories.await())
    }

}