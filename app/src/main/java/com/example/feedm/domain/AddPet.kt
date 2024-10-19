package com.example.feedm.domain

import com.example.feedm.data.model.PetModel
import com.example.feedm.data.PetsRepository
import javax.inject.Inject

class AddPet @Inject constructor(private val repository: PetsRepository) {
    operator fun invoke(pet: PetModel) = repository.addPet(pet)
}