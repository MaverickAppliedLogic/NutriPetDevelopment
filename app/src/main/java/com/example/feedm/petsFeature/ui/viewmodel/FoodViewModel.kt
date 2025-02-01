package com.example.feedm.petsFeature.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feedm.core.domain.model.FoodModel
import com.example.feedm.petsFeature.domain.foodsUseCases.AddFoodToPetUseCase
import com.example.feedm.petsFeature.domain.foodsUseCases.AddFoodUseCase
import com.example.feedm.petsFeature.domain.foodsUseCases.DeleteFoodUseCase
import com.example.feedm.petsFeature.domain.foodsUseCases.GetFoodUseCase
import com.example.feedm.petsFeature.domain.foodsUseCases.GetFoodsByPetIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodViewModel @Inject constructor(
    private val getFoodUseCase: GetFoodUseCase,
    private val addFoodUseCase: AddFoodUseCase,
    private val deleteFoodUseCase: DeleteFoodUseCase,
    private val getFoodsByPetIdUseCase: GetFoodsByPetIdUseCase,
    private val addFoodToPetUseCase: AddFoodToPetUseCase
) : ViewModel() {

    private val _foodAddedId = MutableLiveData<Int>()
    val foodAddedId: LiveData<Int> = _foodAddedId
    private val _food = MutableLiveData<FoodModel>()
    val food: LiveData<FoodModel> = _food
    private val _foods = MutableLiveData<List<FoodModel>>()
    val foods: LiveData<List<FoodModel>> = _foods


    fun getFood(foodId: Int) {
        viewModelScope.launch {
            try {
                val food = getFoodUseCase(foodId)
                _food.value = food
            } catch (e: Exception) {
                // Handle the error here, e.g., log it or show a message
                // You can also set an empty list to _food.value if you want to show an empty state
                println("Error getting food: ${e.message}")
            }
        }
    }

    fun getFoodsByPetId(petId: Int) {
        viewModelScope.launch {
            val foodsList = getFoodsByPetIdUseCase(petId)
            _foods.value = foodsList
        }
    }

    fun addFoodToPet(petId: Int) {
        viewModelScope.launch {
            addFoodToPetUseCase(petId, _foodAddedId.value!!)
        }
    }


    fun addFood(food: FoodModel) {
        viewModelScope.launch {
            try {
                val foodId = addFoodUseCase(food)
                _foodAddedId.value = foodId
            } catch (e: Exception) {
                // Handle the error here
                println("Error adding food: ${e.message}")
            }
        }
    }

    fun deleteFood(food: FoodModel) {
        viewModelScope.launch {
            try {
                deleteFoodUseCase(food)
            } catch (e: Exception) {
                // Handle the error here
                println("Error deleting food: ${e.message}")
            }
        }
    }

}