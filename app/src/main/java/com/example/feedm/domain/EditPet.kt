package com.example.feedm.domain

import com.example.feedm.data.PetsRepository
import com.example.feedm.data.database.entities.toDataBase
import com.example.feedm.data.local.toStorage
import com.example.feedm.domain.model.Pet
import javax.inject.Inject

class EditPet @Inject constructor(private val repository: PetsRepository) {
    suspend operator fun invoke(pet: Pet) {
        repository.updatePet(pet.toDataBase())
    }
}