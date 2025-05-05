package com.example.feedm.petsFeature.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feedm.petsFeature.domain.objectTasks.pet.model.PetModel
import com.example.feedm.petsFeature.domain.objectTasks.pet.useCase.AddPetUseCase
import com.example.feedm.petsFeature.domain.objectTasks.pet.util.PetValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddPetViewmodel @Inject constructor(
    private val addPetUseCase: AddPetUseCase,
    private val petValidator: PetValidator
):ViewModel(){

    private val _petToBeAdded = MutableStateFlow(PetModel(
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

    val petToBeAdded : StateFlow<PetModel> = _petToBeAdded

    fun petChanged(petModel: PetModel){
        _petToBeAdded.value = petModel
    }

    fun validatePet(): List<Int> {
       return petValidator.validatePet(_petToBeAdded.value)
    }

    fun addPet(petModel: PetModel) {
        viewModelScope.launch {
            addPetUseCase(petModel)
        }
    }


}