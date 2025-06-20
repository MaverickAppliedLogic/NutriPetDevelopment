package com.maverickapps.nutripet.petsFeature.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maverickapps.nutripet.core.domain.useCase.CheckCurrentVerIsLatestUseCase
import com.maverickapps.nutripet.core.domain.useCase.FetchCurrentVerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedDataViewmodel @Inject constructor(
    private val checkCurrentVerIsLatestUseCase: CheckCurrentVerIsLatestUseCase,
    private val fetchCurrentVerUseCase: FetchCurrentVerUseCase
): ViewModel(){

    private val _selectedPetId = MutableStateFlow(null as Int?)
    private val _selectedMealId = MutableStateFlow(null as Int?)
    private val _selectedFoodId = MutableStateFlow(null as Int?)
    private val _showDialog = MutableStateFlow(false)

    val selectedPetId = _selectedPetId
    val selectedMealId = _selectedMealId
    val selectedFoodId = _selectedFoodId
    val showDialog = _showDialog

    init {
        mustShowVerNotes()
    }

    private fun mustShowVerNotes(){
        viewModelScope.launch {
        _showDialog.value = checkCurrentVerIsLatestUseCase()
        }
    }

    fun fetchCurrentVer(){
        viewModelScope.launch {
            fetchCurrentVerUseCase()
            _showDialog.value = false
        }
    }

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