package com.maverickapps.tailyCare.petsFeature.domain.objectTasks.pet.useCase


import com.maverickapps.tailyCare.petsFeature.data.PetsRepository
import javax.inject.Inject

class DeletePetUseCase @Inject constructor(
    private val repository: PetsRepository
) {
   suspend operator fun invoke(petId: Int){
       println("DeletePetUseCase $petId")
       val pets = repository.getAllPetsFromDB()
       if(pets.size==1){
           repository.deleteAllPetsFromStorage()
       }
       repository.deletePet(petId)

   }
}