package com.example.feedm.petsFeature.domain


import com.example.feedm.petsFeature.data.PetsRepository
import com.example.feedm.core.database.entities.toDataBase
import com.example.feedm.petsFeature.domain.model.Pet
import javax.inject.Inject

class AddPet @Inject constructor(private val repository: PetsRepository) {
   suspend operator fun invoke(pet: Pet){
        repository.insertPet(pet.toDataBase())
    }
}