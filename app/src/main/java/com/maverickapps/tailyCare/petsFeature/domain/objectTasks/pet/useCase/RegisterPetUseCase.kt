package com.maverickapps.tailyCare.petsFeature.domain.objectTasks.pet.useCase


import com.maverickapps.tailyCare.core.data.database.entities.toDataBase
import com.maverickapps.tailyCare.petsFeature.data.PetsRepository
import com.maverickapps.tailyCare.petsFeature.domain.objectTasks.pet.model.PetModel
import javax.inject.Inject

class RegisterPetUseCase @Inject constructor(private val repository: PetsRepository) {
   suspend operator fun invoke(petModel: PetModel){
       if(petModel.petId != 0){
           repository.updatePet(petModel.toDataBase())
       }
       else{
        repository.insertPet(petModel.toDataBase())
       }
    }
}