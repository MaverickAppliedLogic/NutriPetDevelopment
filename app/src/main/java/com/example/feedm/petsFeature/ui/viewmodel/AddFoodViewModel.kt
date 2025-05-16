package com.example.feedm.petsFeature.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feedm.petsFeature.domain.objectTasks.food.model.FoodModel
import com.example.feedm.petsFeature.domain.objectTasks.food.useCase.AddFoodUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddFoodViewModel @Inject constructor(
    private val addFoodUseCase: AddFoodUseCase
) : ViewModel() {

    private val _initialFood = FoodModel(foodName = "", brand = "", foodWeight = 0f, calories = 0f)

    private val _foodToBeAdded = MutableStateFlow(_initialFood)
    val foodToBeAdded: StateFlow<FoodModel> = _foodToBeAdded

    fun setInitialFood() {
        _foodToBeAdded.value = _initialFood
    }

    fun foodChanged(foodModel: FoodModel) {
        _foodToBeAdded.value = foodModel
    }

    fun addFood() {
        viewModelScope.launch {
            addFoodUseCase(_foodToBeAdded.value)
        }
    }

}