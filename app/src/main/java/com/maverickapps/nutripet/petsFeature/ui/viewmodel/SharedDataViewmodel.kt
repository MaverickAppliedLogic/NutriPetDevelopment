package com.maverickapps.nutripet.petsFeature.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.maverickapps.nutripet.petsFeature.domain.otherTasks.useCase.GetUpdateStateUseCase
import com.maverickapps.nutripet.petsFeature.domain.otherTasks.useCase.SetUpdateStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class SharedDataViewmodel @Inject constructor(
    private val getUpdateStateUseCase: GetUpdateStateUseCase,
    private val setUpdateStateUseCase: SetUpdateStateUseCase
): ViewModel(){

    private val _selectedPetId = MutableStateFlow(null as Int?)
    private val _selectedMealId = MutableStateFlow(null as Int?)
    private val _selectedFoodId = MutableStateFlow(null as Int?)
    private val _isUpdated = MutableStateFlow(false)

    val selectedPetId = _selectedPetId
    val selectedMealId = _selectedMealId
    val selectedFoodId = _selectedFoodId
    val isUpdated = _isUpdated

    init {
        getUpdateState()
    }

    private fun getUpdateState(){
        _isUpdated.value = getUpdateStateUseCase()
    }

    fun setUpdateState(isUpdated: Boolean){
        setUpdateStateUseCase(isUpdated)
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