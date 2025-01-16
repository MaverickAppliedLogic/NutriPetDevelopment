package com.example.feedm.mealsFeature.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.feedm.core.domain.model.MealModel
import com.example.feedm.mealsFeature.domain.AddMeal
import com.example.feedm.mealsFeature.domain.DeleteMeal
import com.example.feedm.mealsFeature.domain.GetMeals
import javax.inject.Inject

class MealsViewmodel @Inject constructor(
    private val getMealsUseCase: GetMeals,
    private val addMealUseCase: AddMeal,
    private val deleteMealUseCase: DeleteMeal
) {

    private val _meals = MutableLiveData<List<MealModel>>()
    val meals: LiveData<List<MealModel>> = _meals

    suspend fun getMeals(petId: Int){
        val result = getMealsUseCase(petId)
        _meals.value = result
    }

    suspend fun addMeal(mealModel: MealModel){
        addMealUseCase(mealModel)
    }

    suspend fun deleteMeal(mealId: Int){
        deleteMealUseCase(mealId)
    }
}