package com.example.feedm.petMealsFeature.data

import com.example.feedm.core.data.database.dao.MealDao
import com.example.feedm.core.data.database.dao.PetDao
import com.example.feedm.core.domain.model.MealModel
import com.example.feedm.core.domain.model.PetModel
import com.example.feedm.core.domain.model.toDomain
import com.example.feedm.petMealsFeature.domain.model.PetMealsModel
import javax.inject.Inject

class PetMealsRepository @Inject constructor(
    private val petDao: PetDao,
    private val mealDao: MealDao
) {

    suspend fun getPetById(petId: Int): PetModel{
        return petDao.getPetById(petId).toDomain()
    }

   suspend fun getAllPetMeals(petId: Int) : List<MealModel>{
        val meals = mealDao.getMealsByPetId(petId).map { it.toDomain() }
        return meals
    }
}