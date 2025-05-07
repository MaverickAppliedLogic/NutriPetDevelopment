package com.example.feedm.petsFeature.domain.objectTasks.pet.useCase


import com.example.feedm.core.data.database.entities.toDataBase
import com.example.feedm.petsFeature.data.PetsRepository
import com.example.feedm.petsFeature.domain.objectTasks.pet.model.PetModel
import javax.inject.Inject

class RegisterPetUseCase @Inject constructor(private val repository: PetsRepository) {
   suspend operator fun invoke(petModel: PetModel){
       if(repository.getPetById(petModel.petId).petId != -1){
           repository.updatePet(petModel.toDataBase())
       }
       else{
        repository.insertPet(petModel.toDataBase())
       }
    }
}