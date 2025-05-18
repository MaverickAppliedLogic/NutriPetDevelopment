package com.example.feedm.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feedm.petsFeature.domain.objectTasks.food.model.FoodModel
import com.example.feedm.petsFeature.domain.objectTasks.food.useCase.DeleteFoodUseCase
import com.example.feedm.petsFeature.domain.objectTasks.food.useCase.GetAllFoodsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodsListViewmodel @Inject constructor(
    private val getAllFoodsUseCase: GetAllFoodsUseCase,
    private val deleteFoodUseCase: DeleteFoodUseCase
) : ViewModel() {

    private val _foods = MutableStateFlow<List<FoodModel>>(emptyList())
    val foods: StateFlow<List<FoodModel>> = _foods

    init {
        fetchData()
    }

    private fun fetchData(){
        viewModelScope.launch {
            _foods.value = getAllFoodsUseCase()
        }
    }

    fun deleteFood(food: FoodModel) {
        viewModelScope.launch {
            deleteFoodUseCase(food)
            fetchData()
        }
    }
}