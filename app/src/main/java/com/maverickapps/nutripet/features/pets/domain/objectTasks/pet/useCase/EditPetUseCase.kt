package com.maverickapps.nutripet.features.pets.domain.objectTasks.pet.useCase

import com.maverickapps.nutripet.features.pets.data.PetsRepository
import com.maverickapps.nutripet.core.data.database.entities.toDataBase
import com.maverickapps.nutripet.features.pets.domain.objectTasks.pet.model.PetModel
import javax.inject.Inject

class EditPetUseCase @Inject constructor(private val repository: PetsRepository) {
    suspend operator fun invoke(petModel: PetModel) {
        repository.updatePet(petModel.toDataBase())
    }
}