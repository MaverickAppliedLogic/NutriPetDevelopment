package com.maverickapps.nutripet.features.pets.domain.objectTasks.pet.useCase

import com.maverickapps.nutripet.features.pets.domain.objectTasks.pet.model.PetModel
import com.maverickapps.nutripet.features.pets.data.repositories.PetsRepository
import javax.inject.Inject

class GetPetByIdUseCase @Inject constructor(private val repository: PetsRepository) {

    suspend operator fun invoke(petId: Int): PetModel {
        return repository.getPetById(petId)
    }
}