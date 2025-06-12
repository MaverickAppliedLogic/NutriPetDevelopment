package com.maverickapps.tailyCare.petsFeature.domain.otherTasks.useCase

import com.maverickapps.tailyCare.petsFeature.domain.objectTasks.meal.useCase.GetMealsUseCase
import com.maverickapps.tailyCare.petsFeature.domain.objectTasks.pet.useCase.GetPetByIdUseCase
import com.maverickapps.tailyCare.petsFeature.domain.objectTasks.petFood.useCase.GetFoodsByPetIdUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetPetsDetailsUseCase @Inject constructor(
    private val getPetByIdUseCase: GetPetByIdUseCase,
    private val getMealsUseCase: GetMealsUseCase,
    private val getFoodsByPetIdUseCase: GetFoodsByPetIdUseCase,
    private val calculateCaloriesUseCase: CalculateCaloriesUseCase
) {

    suspend operator fun invoke(petId: Int) = withContext(Dispatchers.IO){

        val pet = async { getPetByIdUseCase(petId)}
        val meals = async { getMealsUseCase(petId)}
        val foods = async { getFoodsByPetIdUseCase(petId)}
        val calories = async { calculateCaloriesUseCase(pet.await())}

        return@withContext arrayListOf(pet.await(),meals.await(),foods.await(),calories.await())
    }

}