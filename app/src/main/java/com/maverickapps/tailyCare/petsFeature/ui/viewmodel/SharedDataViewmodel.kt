package com.maverickapps.tailyCare.petsFeature.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class SharedDataViewmodel : ViewModel(){

    val _selectedPetId = MutableStateFlow(null as Int?)
    val _selectedMealId = MutableStateFlow(null as Int?)
    val _selectedFoodId = MutableStateFlow(null as Int?)

    val selectedPetId = _selectedPetId
    val selectedMealId = _selectedMealId
    val selectedFoodId = _selectedFoodId

    fun fetchPetId(petId: Int?){
        _selectedPetId.value = petId
    }

    fun fetchMealId(mealId: Int?){
        _selectedMealId.value = mealId
    }

    fun fetchFoodId(foodId: Int?){
        _selectedFoodId.value = foodId
    }

    fun clearPetId(){
        _selectedPetId.value = null
    }

    fun clearMealId(){
        _selectedMealId.value = null
    }

    fun clearFoodId(){
        _selectedFoodId.value = null
    }

    fun wipeData(){
        _selectedPetId.value = null
        _selectedMealId.value = null
        _selectedFoodId.value = null
    }
}