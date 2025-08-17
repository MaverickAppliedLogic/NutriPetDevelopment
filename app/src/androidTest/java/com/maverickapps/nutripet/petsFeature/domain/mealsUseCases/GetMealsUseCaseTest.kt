package com.maverickapps.nutripet.petsFeature.domain.mealsUseCases

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.maverickapps.nutripet.core.data.database.AppDatabase
import com.maverickapps.nutripet.core.data.database.dao.MealDao
import com.maverickapps.nutripet.features.events.domain.useCase.CheckDayChangedUseCase
import com.maverickapps.nutripet.features.pets.data.repositories.MealsRepository
import com.maverickapps.nutripet.features.pets.domain.objectTasks.meal.useCase.DeleteNotDailyMealsUseCase
import com.maverickapps.nutripet.features.pets.domain.objectTasks.meal.useCase.EditMealUseCase
import com.maverickapps.nutripet.features.pets.domain.objectTasks.meal.useCase.GetDailyMealsUseCase
import com.maverickapps.nutripet.features.pets.domain.objectTasks.meal.useCase.GetMealsByPetIdUseCase
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
class GetMealsByPetIdUseCaseTest {

    @MockK
    private lateinit var checkDayChangedUseCase: CheckDayChangedUseCase

    @MockK
    private lateinit var editMealUseCase: EditMealUseCase

    @MockK
    private lateinit var deleteNotDailyMealsUseCase: DeleteNotDailyMealsUseCase


    private lateinit var mealDao: MealDao
    private lateinit var db: AppDatabase
    private lateinit var mealsRepository: MealsRepository
    private lateinit var updateMealsDayChangedUseCase: UpdateMealsDayChangedUseCase
    private lateinit var getMealsByPetIdUseCase: GetMealsByPetIdUseCase
    private lateinit var getDailyMealsUseCase: GetDailyMealsUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        val context = ApplicationProvider.getApplicationContext<android.content.Context>()
        db = Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java).allowMainThreadQueries().build()
        mealDao = db.getMealDao()
        mealsRepository = MealsRepository(mealDao)
        getDailyMealsUseCase = GetDailyMealsUseCase(mealsRepository)
        updateMealsDayChangedUseCase = UpdateMealsDayChangedUseCase(
            getDailyMealsUseCase,deleteNotDailyMealsUseCase, editMealUseCase)
        getMealsByPetIdUseCase = GetMealsByPetIdUseCase(mealsRepository)
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
            val list = getMealsByPetIdUseCase(1)

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
            val list = getMealsByPetIdUseCase(1)

            //Must
            println(list.toString())
            assert(list.isNotEmpty())
        }

    }

}