package com.maverickapps.nutripet.petsFeature.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maverickapps.nutripet.petsFeature.domain.objectTasks.pet.model.PetModel
import com.maverickapps.nutripet.petsFeature.domain.objectTasks.pet.useCase.GetPetByIdUseCase
import com.maverickapps.nutripet.petsFeature.domain.objectTasks.pet.useCase.RegisterPetUseCase
import com.maverickapps.nutripet.petsFeature.domain.objectTasks.pet.util.PetValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterPetViewmodel @Inject constructor(
    private val registerPetUseCase: RegisterPetUseCase,
    private val getPetByIdUseCase: GetPetByIdUseCase,
    private val petValidator: PetValidator
):ViewModel(){

    private val _initialPet = PetModel(
        petId = 0,
        animal = "dog",
        petName = "",
        age = 0f,
        petWeight = 0f,
        genre = null,
        sterilized = false,
        activity = null,
        goal = "",
        allergies = null
    )
    private val _petToBeRegistered = MutableStateFlow(_initialPet)

    val petToBeRegistered : StateFlow<PetModel> = _petToBeRegistered

    fun setInitialPet(){
        _petToBeRegistered.value = _initialPet
    }

    fun getPetById(petId: Int): Boolean {
        viewModelScope.launch {
            val result = getPetByIdUseCase(petId)
            _petToBeRegistered.value = result
        }
        return true
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