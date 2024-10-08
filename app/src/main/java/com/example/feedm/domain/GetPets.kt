package com.example.feedm.domain

import android.content.Context
import com.example.feedm.data.model.PetModel
import com.example.feedm.data.model.PetsRepository

class GetPets(context: Context) {
    private val repository = PetsRepository(context)

    operator fun invoke(): ArrayList<PetModel>?= repository.getAllPets()
}