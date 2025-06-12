package com.maverickapps.tailyCare.petsFeature.domain.objectTasks.pet.useCase

import com.maverickapps.tailyCare.petsFeature.data.PetsRepository
import com.maverickapps.tailyCare.core.data.database.entities.toDataBase
import com.maverickapps.tailyCare.petsFeature.domain.objectTasks.pet.model.PetModel
import javax.inject.Inject

class EditPetUseCase @Inject constructor(private val repository: PetsRepository) {
    suspend operator fun invoke(petModel: PetModel) {
        repository.updatePet(petModel.toDataBase())
    }
}