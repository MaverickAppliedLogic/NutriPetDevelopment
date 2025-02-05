package com.example.feedm.petsFeature.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feedm.petsFeature.domain.model.MealModel
import com.example.feedm.petsFeature.domain.mealsUseCases.AddMealUseCase
import com.example.feedm.petsFeature.domain.mealsUseCases.DeleteMealUseCase
import com.example.feedm.petsFeature.domain.mealsUseCases.GetMealsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealsViewmodel @Inject constructor(
    private val getMealsUseCase: GetMealsUseCase,
    private val addMealUseCase: AddMealUseCase,
    private val deleteMealUseCase: DeleteMealUseCase,

): ViewModel() {

    private val _meals = MutableLiveData<List<MealModel>>()
    val meals: LiveData<List<MealModel>> = _meals

    fun getMeals(petId: Int){
        viewModelScope.launch {
            val result = getMealsUseCase(petId)
            _meals.value = result
        }

    }


    fun addMeal(mealModel: MealModel?,
                ration: Float?,
                hour: Int?,
                min: Int?,
                mealCalories: Double?,
                petId: Int?){
        viewModelScope.launch {
        addMealUseCase(mealModel = mealModel,
            ration = ration, hour = hour, min = min, mealCalories =  mealCalories, petId =  petId)
        }
    }

    suspend fun deleteMeal(mealId: Int){
        deleteMealUseCase(mealId)
    }
}