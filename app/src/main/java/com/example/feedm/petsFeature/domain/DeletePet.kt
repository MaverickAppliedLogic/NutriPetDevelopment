package com.example.feedm.petsFeature.domain


import com.example.feedm.petsFeature.data.PetsRepository
import com.example.feedm.core.database.entities.toDataBase
import com.example.feedm.petsFeature.domain.model.Pet
import javax.inject.Inject

class DeletePet @Inject constructor(
    private val repository: PetsRepository
) {
   suspend operator fun invoke(pet: Pet){
       val pets = repository.getAllPetsFromDB()
       if(pets.size==1){
           repository.deleteAllPetsFromStorage()
       }
       repository.deletePet(pet.toDataBase())

   }
}