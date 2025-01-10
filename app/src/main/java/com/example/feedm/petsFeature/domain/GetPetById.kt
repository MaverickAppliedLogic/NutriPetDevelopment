package com.example.feedm.petsFeature.domain

import com.example.feedm.core.domain.model.PetModel
import com.example.feedm.petsFeature.data.PetsRepository
import javax.inject.Inject

class GetPetById @Inject constructor(private val repository: PetsRepository) {

    suspend operator fun invoke(petId: Int): PetModel{
        return repository.getPetById(petId)
    }
}