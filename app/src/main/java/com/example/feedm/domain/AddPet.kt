package com.example.feedm.domain

import android.content.Context
import com.example.feedm.data.model.PetModel
import com.example.feedm.data.model.PetsRepository

class AddPet(context: Context) {
    private val repository = PetsRepository(context)

    operator fun invoke(pet: PetModel) = repository.addPet(pet)
}