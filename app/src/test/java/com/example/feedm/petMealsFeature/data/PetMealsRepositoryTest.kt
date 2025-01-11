package com.example.feedm.petMealsFeature.data

import com.example.feedm.core.data.database.dao.MealDao
import com.example.feedm.core.data.database.dao.PetDao
import com.example.feedm.core.data.database.entities.MealEntity
import com.example.feedm.core.data.database.entities.PetEntity
import com.example.feedm.core.domain.model.MealModel
import com.example.feedm.core.domain.model.PetModel
import com.example.feedm.petMealsFeature.domain.GetPetMeals
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*


class PetMealsRepositoryTest(){

    private lateinit var mealDao: MealDao
    private lateinit var petDao: PetDao

    private lateinit var repository: PetMealsRepository

    @Before
    fun setup(){
        petDao = mockk()
        mealDao = mockk()
        repository = PetMealsRepository(petDao,mealDao)
    }

    @Test
    fun `petDao returns a pet but mealDao don't, should return emptyList`(){
        runBlocking {
            //Give
            val pet = PetEntity(
                1,
                1,
                "dog",
                "toby",
                3.0f,
                25.0f,
                "macho",
                true,
                "baja",
                "bajar peso",
                "")

            val meals = listOf(MealModel(1,1,1,0.0f))
            coEvery { petDao.getPetById(1) } returns pet
            coEvery { mealDao.getMealsByPetId(1) } returns emptyList()

            //When
            val result = repository.getAllPetMeals(1)

            //Then
            assert(result.isEmpty())
        }
    }

    @Test
    fun `petDao returns a pet and mealDao returns a ListMealEntity, should return ListMealModel`(){
        runBlocking {
            //Give
            val pet = PetEntity(
                1,
                1,
                "dog",
                "toby",
                3.0f,
                25.0f,
                "macho",
                true,
                "baja",
                "bajar peso",
                "")

            val meals = listOf(MealEntity(1,1,1,0.0f))
            coEvery { petDao.getPetById(1) } returns pet
            coEvery { mealDao.getMealsByPetId(1) } returns meals

            //When
            val result = repository.getAllPetMeals(1)

            //Then
            assert(result.get(0) is MealModel)
        }
    }
}