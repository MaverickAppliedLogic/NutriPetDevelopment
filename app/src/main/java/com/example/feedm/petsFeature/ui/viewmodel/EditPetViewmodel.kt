package com.example.feedm.petsFeature.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feedm.petsFeature.domain.objectTasks.pet.model.PetModel
import com.example.feedm.petsFeature.domain.objectTasks.pet.useCase.EditPetUseCase
import com.example.feedm.petsFeature.domain.objectTasks.pet.useCase.GetPetByIdUseCase
import com.example.feedm.petsFeature.domain.objectTasks.pet.useCase.RegisterPetUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditPetViewmodel @Inject constructor (
    private val getPetByIdUseCase: GetPetByIdUseCase,
    private val registerPetUseCase: RegisterPetUseCase,
    private val editPetUseCase: EditPetUseCase
): ViewModel() {
    private val _petModel = MutableLiveData<PetModel>()
    val petModel: LiveData<PetModel> = _petModel



    fun editPetNotCommitting(petModel: PetModel){
        viewModelScope.launch {
            _petModel.value = petModel
        }
    }

    fun addPet(petModel: PetModel) {
        viewModelScope.launch {
            registerPetUseCase(petModel)
        }
    }

    fun editPet(petModel: PetModel) {
        viewModelScope.launch {
            editPetUseCase(petModel)
        }
    }
}