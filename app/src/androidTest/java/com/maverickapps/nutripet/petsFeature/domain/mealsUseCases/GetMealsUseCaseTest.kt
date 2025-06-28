package com.maverickapps.nutripet.petsFeature.domain.mealsUseCases

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.maverickapps.nutripet.core.data.database.AppDatabase
import com.maverickapps.nutripet.core.data.database.dao.MealDao
import com.maverickapps.nutripet.features.events.domain.useCase.CheckDayChangedUseCase
import com.maverickapps.nutripet.features.pets.data.MealsRepository
import com.maverickapps.nutripet.features.pets.domain.objectTasks.meal.useCase.GetMealsUseCase
import com.maverickapps.nutripet.features.pets.domain.objectTasks.meal.useCase.UpdateMealsDayChangedUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GetMealsUseCaseTest {

    @MockK
    private lateinit var checkDayChangedUseCase: CheckDayChangedUseCase

    private lateinit var mealDao: MealDao
    private lateinit var db: AppDatabase
    private lateinit var mealsRepository: MealsRepository
    private lateinit var updateMealsDayChangedUseCase: UpdateMealsDayChangedUseCase
    private lateinit var getMealsUseCase: GetMealsUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        val context = ApplicationProvider.getApplicationContext<android.content.Context>()
        db = Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java).allowMainThreadQueries().build()
        mealDao = db.getMealDao()
        mealsRepository = MealsRepository(mealDao)
        updateMealsDayChangedUseCase = UpdateMealsDayChangedUseCase(mealsRepository)
        getMealsUseCase = GetMealsUseCase(mealsRepository, checkDayChangedUseCase, updateMealsDayChangedUseCase)
    }

    @After
    fun tearDown(){
        db.close()
    }

    @Test
    fun itMustReturnAnEmptyList() {
        runBlocking {
            //Given
            coEvery { checkDayChangedUseCase()} returns true

            //When
            val list = getMealsUseCase(1)

            //Must
            assert(list.isEmpty())
        }

    }

    @Test
    fun itMustReturnAList() {
        runBlocking {
            //Given
            coEvery { checkDayChangedUseCase()} returns false

            //When
            val list = getMealsUseCase(1)

            //Must
            println(list.toString())
            assert(list.isNotEmpty() && list.size == 2)
        }

    }

}