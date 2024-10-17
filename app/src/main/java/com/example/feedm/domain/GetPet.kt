package com.example.feedm.domain

import com.example.feedm.data.model.PetsRepository
import javax.inject.Inject

class GetPet @Inject constructor(private val repository: PetsRepository) {
    operator fun invoke(pos: Int) = repository.getPet(pos)
}