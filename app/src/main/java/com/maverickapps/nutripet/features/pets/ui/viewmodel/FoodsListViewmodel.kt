package com.maverickapps.nutripet.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maverickapps.nutripet.features.pets.domain.objectTasks.food.model.FoodModel
import com.maverickapps.nutripet.features.pets.domain.objectTasks.food.useCase.DeleteFoodUseCase
import com.maverickapps.nutripet.features.pets.domain.objectTasks.food.useCase.GetAllFoodsUseCase
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

    fun fetchData(){
        viewModelScope.launch {
            _foods.value = getAllFoodsUseCase()
        }
    }

    fun deleteFood(id: Int) {
        viewModelScope.launch {
            deleteFoodUseCase(id)
            fetchData()
        }
    }
}