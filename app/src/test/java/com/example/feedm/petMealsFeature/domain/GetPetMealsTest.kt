package com.example.feedm.petMealsFeature.domain

import com.example.feedm.core.domain.model.MealModel
import com.example.feedm.core.domain.model.PetModel
import com.example.feedm.petMealsFeature.data.PetMealsRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetPetMealsTest {
    private lateinit var repository: PetMealsRepository
    private lateinit var getPetMeals: GetPetMeals

    @Before
    fun setUp(){
        repository = mockk()
        getPetMeals = GetPetMeals(repository)
    }

    @Test
    fun `should return an  petMealsModel from repository`(){
        runBlocking {
            //Given
            val pet = PetModel(
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

            val meals = listOf(MealModel(1,1,1,0,0.0f))

            coEvery { repository.getPetById(1) } returns pet
            coEvery { repository.getAllPetMeals(1) } returns meals

            //When
            val result = getPetMeals(1)

            //Then
            assert(result.meals.equals(meals) && result.pet.equals(pet))
        }
    }
}