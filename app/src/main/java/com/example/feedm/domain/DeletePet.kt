package com.example.feedm.domain

import com.example.feedm.data.PetsRepository
import com.example.feedm.data.database.entities.toDataBase
import com.example.feedm.data.local.toStorage
import com.example.feedm.domain.model.Pet
import javax.inject.Inject

class DeletePet @Inject constructor(
    private val repository: PetsRepository) {
   suspend operator fun invoke(pet: Pet){
       val pets = repository.getAllPetsFromDB()
       if(pets.size==1){
           repository.deleteAllPetsFromStorage()
       }
       repository.deletePet(pet.toDataBase())

   }
}