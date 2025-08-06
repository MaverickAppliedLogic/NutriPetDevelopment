package com.maverickapps.nutripet.features.pets.domain.otherTasks.useCase

import com.maverickapps.nutripet.features.pets.domain.objectTasks.meal.useCase.GetMealsByPetIdUseCase
import com.maverickapps.nutripet.features.pets.domain.objectTasks.pet.useCase.GetPetByIdUseCase
import com.maverickapps.nutripet.features.pets.domain.objectTasks.petFood.useCase.GetFoodsByPetIdUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetPetsDetailsUseCase @Inject constructor(
    private val getPetByIdUseCase: GetPetByIdUseCase,
    private val getMealsByPetIdUseCase: GetMealsByPetIdUseCase,
    private val getFoodsByPetIdUseCase: GetFoodsByPetIdUseCase,
    private val calculateCaloriesUseCase: CalculateCaloriesUseCase
) {

    suspend operator fun invoke(petId: Int) = withContext(Dispatchers.IO){

        val pet = async { getPetByIdUseCase(petId)}
        val meals = async { getMealsByPetIdUseCase(petId)}
        val foods = async { getFoodsByPetIdUseCase(petId)}
        val calories = async { calculateCaloriesUseCase(pet.await())}

        return@withContext arrayListOf(pet.await(),meals.await(),foods.await(),calories.await())
    }

}