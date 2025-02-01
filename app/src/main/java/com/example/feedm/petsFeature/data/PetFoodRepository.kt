package com.example.feedm.petsFeature.data

import com.example.feedm.core.data.database.dao.PetFoodDao
import com.example.feedm.core.data.database.entities.PetFoodEntity
import jakarta.inject.Inject

class PetFoodRepository @Inject constructor(
    private val petFoodDao: PetFoodDao
) {
    suspend fun getFoodsIdByPetId(petId: Int): List<Int> {
        return petFoodDao.getFoodsIdByPetId(petId)
    }
}