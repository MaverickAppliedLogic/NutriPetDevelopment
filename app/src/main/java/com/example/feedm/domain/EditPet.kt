package com.example.feedm.domain

import com.example.feedm.data.model.PetModel
import com.example.feedm.data.PetsRepository
import javax.inject.Inject

class EditPet @Inject constructor(private val repository: PetsRepository) {
    operator fun invoke(pet: PetModel, pos: Int)= repository.editPet(pet, pos)
}