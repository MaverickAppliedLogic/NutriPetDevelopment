package com.maverickapps.nutripet.features.pets.data.repositories

import com.maverickapps.nutripet.core.data.database.dao.PetFoodDao
import com.maverickapps.nutripet.core.data.database.entities.toDatabase
import com.maverickapps.nutripet.features.pets.domain.objectTasks.petFood.model.PetFoodModel
import jakarta.inject.Inject

class PetFoodRepository @Inject constructor(
    private val petFoodDao: PetFoodDao
) {
    suspend fun getFoodsIdByPetId(petId: Int): List<Int> {
        return petFoodDao.getFoodsIdByPetId(petId)
    }

    suspend fun addFoodToPet(petFood: PetFoodModel) {
        petFoodDao.addFoodToPet(petFood.toDatabase())
    }

}