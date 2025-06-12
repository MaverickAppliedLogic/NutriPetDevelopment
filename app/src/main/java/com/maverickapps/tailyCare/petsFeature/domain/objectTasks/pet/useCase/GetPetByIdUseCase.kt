package com.maverickapps.tailyCare.petsFeature.domain.objectTasks.pet.useCase

import com.maverickapps.tailyCare.petsFeature.domain.objectTasks.pet.model.PetModel
import com.maverickapps.tailyCare.petsFeature.data.PetsRepository
import javax.inject.Inject

class GetPetByIdUseCase @Inject constructor(private val repository: PetsRepository) {

    suspend operator fun invoke(petId: Int): PetModel {
        return repository.getPetById(petId)
    }
}