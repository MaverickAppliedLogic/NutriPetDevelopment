package com.example.feedm.petsFeature.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feedm.petsFeature.domain.objectTasks.pet.model.PetModel
import com.example.feedm.petsFeature.domain.objectTasks.pet.useCase.AddPetUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddPetViewmodel @Inject constructor(
    private val addPetUseCase: AddPetUseCase
):ViewModel(){

    fun addPet(petModel: PetModel) {
        viewModelScope.launch {
            addPetUseCase(petModel)
        }
    }
}