package com.example.feedm.petsFeature.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feedm.petsFeature.domain.objectTasks.food.model.FoodModel
import com.example.feedm.petsFeature.domain.objectTasks.food.useCase.GetFoodUseCase
import com.example.feedm.petsFeature.domain.objectTasks.meal.model.MealModel
import com.example.feedm.petsFeature.domain.objectTasks.meal.useCase.AddMealUseCase
import com.example.feedm.petsFeature.domain.objectTasks.meal.useCase.GetMealByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddMealViewmodel @Inject constructor(
    private val addMealUseCase: AddMealUseCase,
    private val getFoodUseCase: GetFoodUseCase,
    private val getMealUseCase: GetMealByIdUseCase,
    ): ViewModel() {

        private val initialSelectedFood = FoodModel(
            foodId = -1,
            foodName = "",
            brand = "",
            foodWeight = 0f,
            calories = 0f,)

    private val _mealToBeAdded = MutableStateFlow(
        MealModel(
            petId = 0,
            foodId = 0,
            mealTime = 0,
            ration = 0f
        )
    )

    private val _foodSelected = MutableStateFlow(initialSelectedFood)

    val mealToBeAdded : StateFlow<MealModel> = _mealToBeAdded
    val foodSelected : StateFlow<FoodModel> = _foodSelected

    fun setInitialSelectedFood(){
        _foodSelected.value = initialSelectedFood
    }
    fun mealToBeAddedChanged(mealModel: MealModel) {
        viewModelScope.launch {
            _mealToBeAdded.value = mealModel
        }
    }

    fun getFood(foodId: Int){
        viewModelScope.launch {
            _foodSelected.value = getFoodUseCase(foodId)
            _mealToBeAdded.value = _mealToBeAdded.value.copy(foodId = _foodSelected.value.foodId)
        }
    }

    fun getMeal(mealId: Int){
        viewModelScope.launch {
            _mealToBeAdded.value = getMealUseCase(mealId)
            if (_mealToBeAdded.value.foodId != null) {
                _foodSelected.value = getFoodUseCase(_mealToBeAdded.value.foodId!!)
            }
        }
    }

    fun addMeal(){
        viewModelScope.launch {
            println("Antes de ser agregado: ${_mealToBeAdded.value.mealTime}")
            _mealToBeAdded.value =
                _mealToBeAdded.value.copy(mealCalories = (
                        _foodSelected.value.calories * _mealToBeAdded.value.ration).toDouble() )
        addMealUseCase(_mealToBeAdded.value)
        }
    }


}