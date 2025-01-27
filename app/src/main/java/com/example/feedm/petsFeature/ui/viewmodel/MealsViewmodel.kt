package com.example.feedm.petsFeature.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feedm.core.domain.model.MealModel
import com.example.feedm.petsFeature.domain.mealsUseCases.AddMeal
import com.example.feedm.petsFeature.domain.mealsUseCases.DeleteMeal
import com.example.feedm.petsFeature.domain.mealsUseCases.GetMeals
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealsViewmodel @Inject constructor(
    private val getMealsUseCase: GetMeals,
    private val addMealUseCase: AddMeal,
    private val deleteMealUseCase: DeleteMeal
): ViewModel() {

    private val _meals = MutableLiveData<List<MealModel>>()
    val meals: LiveData<List<MealModel>> = _meals

    fun getMeals(petId: Int){
        viewModelScope.launch {
            val result = getMealsUseCase(petId)
            _meals.value = result
        }

    }

    fun addMeal(mealModel: MealModel){
        viewModelScope.launch {
        addMealUseCase(mealModel)
        }
    }

    suspend fun deleteMeal(mealId: Int){
        deleteMealUseCase(mealId)
    }
}