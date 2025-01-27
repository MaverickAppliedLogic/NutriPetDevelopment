package com.example.feedm.mealsFeature.data

import com.example.feedm.core.data.database.dao.MealDao
import com.example.feedm.core.data.database.entities.MealEntity
import com.example.feedm.core.domain.model.MealModel
import com.example.feedm.petsFeature.data.MealsRepository
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.slot
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class MealsRepositoryTest{

    @RelaxedMockK
    private lateinit var mealsDao: MealDao

    private lateinit var mealsRepository: MealsRepository

    @Before
    fun setUp(){
        MockKAnnotations.init(this)
        mealsRepository = MealsRepository(mealsDao)
    }

    @Test
    fun `it should return a MealModel`(){
        runBlocking {
            //Given

            //When
            val result = mealsRepository.getMealsByPetId(-1)

            //Then
            assert(result is List<MealModel>)
        }

    }

    @Test
    fun `it should send a MealEntity`(){
        runBlocking {
            //Given
            val meal = MealModel(0,0,0,0f)

            //When
             mealsRepository.addMealForAPet(meal)
            val argCaptor = slot<MealEntity>()

            //Then
            coVerify { mealsDao.addMealForAPet(capture(argCaptor)) }
        }

    }



}