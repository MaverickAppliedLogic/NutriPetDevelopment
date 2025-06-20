package com.maverickapps.nutripet.petsFeature.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maverickapps.nutripet.core.domain.useCase.CheckCurrentVerIsLatestUseCase
import com.maverickapps.nutripet.core.domain.useCase.FetchCurrentVerUseCase
import com.maverickapps.nutripet.core.domain.useCase.SendToUpdate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedDataViewmodel @Inject constructor(
    private val checkCurrentVerIsLatestUseCase: CheckCurrentVerIsLatestUseCase,
    private val fetchCurrentVerUseCase: FetchCurrentVerUseCase,
    private val sendToUpdate: SendToUpdate
) : ViewModel() {

    private val _selectedPetId = MutableStateFlow(null as Int?)
    private val _selectedMealId = MutableStateFlow(null as Int?)
    private val _selectedFoodId = MutableStateFlow(null as Int?)
    private val _isRecentlyUpdated = MutableStateFlow(false)
    private val _needToUpdate = MutableStateFlow(false)

    val selectedPetId = _selectedPetId
    val selectedMealId = _selectedMealId
    val selectedFoodId = _selectedFoodId
    val isRecentlyUpdated = _isRecentlyUpdated
    val needToUpdate = _needToUpdate

    init {
        checkUpdateNeeds()
    }

    private fun checkUpdateNeeds() {
        viewModelScope.launch {
            _needToUpdate.value = checkCurrentVerIsLatestUseCase().first
            _isRecentlyUpdated.value =
                if (_needToUpdate.value) false
                else checkCurrentVerIsLatestUseCase().second
        }
    }

    fun updateDialogSeen() {
        viewModelScope.launch {
            if (_needToUpdate.value) {
                sendToUpdate()
            }
            else{
                fetchCurrentVerUseCase()
                _isRecentlyUpdated.value = false
            }
        }
    }

    fun fetchPetId(petId: Int?) {
        _selectedPetId.value = petId
    }

    fun fetchMealId(mealId: Int?) {
        _selectedMealId.value = mealId
    }

    fun fetchFoodId(foodId: Int?) {
        _selectedFoodId.value = foodId
    }

    fun clearPetId() {
        _selectedPetId.value = null
    }

    fun clearMealId() {
        _selectedMealId.value = null
    }

    fun clearFoodId() {
        _selectedFoodId.value = null
    }

    fun wipeData() {
        _selectedPetId.value = null
        _selectedMealId.value = null
        _selectedFoodId.value = null
    }
}