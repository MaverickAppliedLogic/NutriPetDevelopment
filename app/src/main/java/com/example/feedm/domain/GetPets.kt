package com.example.feedm.domain

import com.example.feedm.data.model.PetModel
import com.example.feedm.data.PetsRepository
import javax.inject.Inject

class GetPets @Inject constructor(private val repository: PetsRepository){
    operator fun invoke(): ArrayList<PetModel>?= repository.getAllPets()
}