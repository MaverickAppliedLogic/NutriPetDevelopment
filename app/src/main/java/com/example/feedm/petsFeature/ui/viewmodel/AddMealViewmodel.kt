package com.example.feedm.petsFeature.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feedm.petsFeature.domain.objectTasks.food.model.FoodModel
import com.example.feedm.petsFeature.domain.objectTasks.food.useCase.GetFoodUseCase
import com.example.feedm.petsFeature.domain.objectTasks.meal.model.MealModel
import com.example.feedm.petsFeature.domain.objectTasks.meal.useCase.AddMealUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddMealViewmodel @Inject constructor(
    private val addMealUseCase: AddMealUseCase,
    private val getFoodUseCase: GetFoodUseCase
    ): ViewModel() {

    private val _mealToBeAdded = MutableStateFlow(
        MealModel(
            petId = 0,
            foodId = 0,
            mealTime = 0,
            ration = 0f
        )
    )

    private val _foodSelected = MutableStateFlow(
        FoodModel(
            foodId = -1,
            foodName = "",
            brand = "",
            foodWeight = 0f,
            calories = 0f,)
    )

    val mealToBeAdded : StateFlow<MealModel> = _mealToBeAdded
    val foodSelected : StateFlow<FoodModel> = _foodSelected


    fun mealToBeAddedChanged(mealModel: MealModel) {
        viewModelScope.launch {
            _mealToBeAdded.value = mealModel
        }
    }

    fun getFood(foodId: Int){
        viewModelScope.launch {
            _foodSelected.value = getFoodUseCase(foodId)
        }
    }

    fun addMeal(){
        viewModelScope.launch {
        addMealUseCase(_mealToBeAdded.value)
        }
    }


}