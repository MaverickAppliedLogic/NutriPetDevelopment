package com.example.feedm.petsFeature.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feedm.petsFeature.domain.objectTasks.food.model.FoodModel
import com.example.feedm.petsFeature.domain.objectTasks.food.useCase.AddFoodUseCase
import com.example.feedm.petsFeature.domain.objectTasks.meal.model.MealModel
import com.example.feedm.petsFeature.domain.objectTasks.meal.useCase.AddMealUseCase
import com.example.feedm.petsFeature.domain.objectTasks.meal.useCase.DeleteMealUseCase
import com.example.feedm.petsFeature.domain.objectTasks.meal.useCase.GetMealsUseCase
import com.example.feedm.petsFeature.domain.objectTasks.petFood.useCase.GetFoodsByPetIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddMealViewmodel @Inject constructor(
    private val addMealUseCase: AddMealUseCase,
    private val getFoodsByPetIdUseCase: GetFoodsByPetIdUseCase,
    ): ViewModel() {

    private val _foods = MutableLiveData<List<FoodModel>>()
    val foods: LiveData<List<FoodModel>> = _foods


    fun getFoodsByPetId(petId: Int) {
        viewModelScope.launch {
            val foodsList = getFoodsByPetIdUseCase(petId)
            println("En el VM: " + foodsList.toString())
            _foods.value = foodsList
        }
    }


    fun addMeal(mealModel: MealModel?,
                ration: Float?,
                hour: Int?,
                min: Int?,
                mealCalories: Double?,
                food: FoodModel,
                petId: Int?){
        viewModelScope.launch {
        addMealUseCase(
            mealModel = mealModel,
            ration = ration, hour = hour, min = min,
            mealCalories =  mealCalories, food = food, petId =  petId)
        }
    }

}