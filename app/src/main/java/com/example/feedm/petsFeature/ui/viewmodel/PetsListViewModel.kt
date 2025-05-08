package com.example.feedm.ui.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feedm.petsFeature.domain.objectTasks.pet.useCase.RegisterPetUseCase
import com.example.feedm.petsFeature.domain.objectTasks.pet.useCase.DeletePetUseCase
import com.example.feedm.petsFeature.domain.objectTasks.pet.useCase.EditPetUseCase
import com.example.feedm.petsFeature.domain.objectTasks.pet.useCase.GetPetsUseCase
import com.example.feedm.petsFeature.domain.objectTasks.pet.model.PetModel
import com.example.feedm.petsFeature.domain.objectTasks.pet.useCase.GetPetByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PetsListViewModel @Inject constructor(
    private val registerPetUseCase: RegisterPetUseCase,
    private val deletePetUseCase: DeletePetUseCase,
    private val editPetUseCase: EditPetUseCase,
    private val getPetsUseCase: GetPetsUseCase,
    private val getPetByIdUseCase: GetPetByIdUseCase
) : ViewModel() {

    private val _pets = MutableLiveData<List<PetModel>>(emptyList())
    val pets: LiveData<List<PetModel>> = _pets

    init {
        getData()
    }

    @SuppressLint("NullSafeMutableLiveData")
    fun getData() {
        viewModelScope.launch {
            val result = getPetsUseCase()
            if (result.isNotEmpty()) {
                _pets.value = result
                Log.i("Depuring", "LLena el _pets")
            }
            else{
                _pets.value = emptyList()
            }
        }
        Log.i("Depuring", "El viewmodelscope terminó")
    }


    fun deletePet(petModel: PetModel) {
        viewModelScope.launch {
            deletePetUseCase(petModel)
            getData()
        }
    }


}