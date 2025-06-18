package com.maverickapps.nutripet.petsFeature.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maverickapps.nutripet.petsFeature.domain.objectTasks.food.model.FoodModel
import com.maverickapps.nutripet.petsFeature.domain.objectTasks.food.useCase.GetFoodUseCase
import com.maverickapps.nutripet.petsFeature.domain.objectTasks.meal.model.MealModel
import com.maverickapps.nutripet.petsFeature.domain.objectTasks.meal.useCase.AddMealUseCase
import com.maverickapps.nutripet.petsFeature.domain.objectTasks.meal.useCase.GetMealByIdUseCase
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
) : ViewModel() {


    private val initialSelectedFood = FoodModel(
        foodId = -1,
        foodName = "",
        brand = "",
        foodWeight = 0f,
        calories = 0f,
    )

    private val initialMeal = MealModel(
        petId = 0,
        foodId = -1,
        mealTime = 0,
        ration = 0f,
        isDailyMeal = false,
    )

    private val _mealToBeAdded = MutableStateFlow(initialMeal)
    private val _foodSelected = MutableStateFlow(initialSelectedFood)
    private val _mealIsValid = MutableStateFlow(Pair<String?, Boolean>(null, true))

    val mealToBeAdded: StateFlow<MealModel> = _mealToBeAdded
    val foodSelected: StateFlow<FoodModel> = _foodSelected
    val mealIsValid: StateFlow<Pair<String?, Boolean>> = _mealIsValid

    fun setInitialMeal() {
        _foodSelected.value = initialSelectedFood
        _mealToBeAdded.value = initialMeal
        _mealIsValid.value = Pair(null, false)
    }

    fun mealToBeAddedChanged(mealModel: MealModel) {
        viewModelScope.launch {
            _mealToBeAdded.value = mealModel
            validateMeal()
        }
    }

    fun getFood(foodId: Int) {
        viewModelScope.launch {
            _foodSelected.value = getFoodUseCase(foodId)
            _mealToBeAdded.value = _mealToBeAdded.value.copy(foodId = _foodSelected.value.foodId)
        }
    }

    fun getMeal(mealId: Int) {
        viewModelScope.launch {
            _mealToBeAdded.value = getMealUseCase(mealId)
            if (_mealToBeAdded.value.foodId != null) {
                _foodSelected.value = getFoodUseCase(_mealToBeAdded.value.foodId!!)
            }
        }
    }

    fun addMeal() {
        viewModelScope.launch {
            _mealToBeAdded.value =
                _mealToBeAdded.value.copy(
                    mealCalories = (
                            (_foodSelected.value.calories / 100) * _mealToBeAdded.value.ration)
                        .toDouble()
                )
            addMealUseCase(_mealToBeAdded.value)
        }
    }

    private fun validateMeal() {
        var isValid = ""
        if (_mealToBeAdded.value.ration <= 0) isValid = "Ration"
        if (_mealToBeAdded.value.foodId == -1) isValid = "Food"
        if (_mealToBeAdded.value.mealTime == 0L) isValid = "Time"
        when (isValid) {
            "Ration" -> _mealIsValid.value = Pair("Ration", false)
            "Food" -> _mealIsValid.value = Pair("Food", false)
            "Time" -> _mealIsValid.value = Pair("Time", false)
            else -> _mealIsValid.value = Pair("", true)
        }
    }


}