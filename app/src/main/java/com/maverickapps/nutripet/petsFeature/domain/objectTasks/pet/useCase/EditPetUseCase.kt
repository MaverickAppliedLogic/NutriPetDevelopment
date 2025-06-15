package com.maverickapps.nutripet.petsFeature.domain.objectTasks.pet.useCase

import com.maverickapps.nutripet.petsFeature.data.PetsRepository
import com.maverickapps.nutripet.core.data.database.entities.toDataBase
import com.maverickapps.nutripet.petsFeature.domain.objectTasks.pet.model.PetModel
import javax.inject.Inject

class EditPetUseCase @Inject constructor(private val repository: PetsRepository) {
    suspend operator fun invoke(petModel: PetModel) {
        repository.updatePet(petModel.toDataBase())
    }
}