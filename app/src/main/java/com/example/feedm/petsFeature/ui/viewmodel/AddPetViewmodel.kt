package com.example.feedm.petsFeature.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feedm.petsFeature.domain.objectTasks.pet.model.PetModel
import com.example.feedm.petsFeature.domain.objectTasks.pet.useCase.GetPetByIdUseCase
import com.example.feedm.petsFeature.domain.objectTasks.pet.useCase.RegisterPetUseCase
import com.example.feedm.petsFeature.domain.objectTasks.pet.util.PetValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddPetViewmodel @Inject constructor(
    private val registerPetUseCase: RegisterPetUseCase,
    private val getPetByIdUseCase: GetPetByIdUseCase,
    private val petValidator: PetValidator
):ViewModel(){

    private val _petToBeRegistered = MutableStateFlow(PetModel(
        animal = "dog",
        petName = "",
        age = 0f,
        petWeight = 0f,
        genre = "",
        sterilized = false,
        activity = "",
        goal = "",
        allergies = ""
    ))

    val petToBeRegistered : StateFlow<PetModel> = _petToBeRegistered

    fun getPetById(petId: Int) {
        viewModelScope.launch {
            val result = getPetByIdUseCase(petId)
            _petToBeRegistered.value = result
        }
    }

    fun petChanged(petModel: PetModel){
        _petToBeRegistered.value = petModel
    }

    fun validatePet(): List<Int> {
       return petValidator.validatePet(_petToBeRegistered.value)
    }


    fun registerPet(petModel: PetModel) {
        viewModelScope.launch {
            registerPetUseCase(petModel)
        }
    }


}