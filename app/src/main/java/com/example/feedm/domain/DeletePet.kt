package com.example.feedm.domain

import android.content.Context
import com.example.feedm.data.model.PetModel
import com.example.feedm.data.model.PetsRepository
import javax.inject.Inject

class DeletePet @Inject constructor(private val repository: PetsRepository) {
    operator fun invoke(pet: PetModel)= repository.deletePet(pet)
}