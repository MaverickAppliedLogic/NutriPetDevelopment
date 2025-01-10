package com.example.feedm.ui.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feedm.petsFeature.domain.AddPet
import com.example.feedm.petsFeature.domain.DeletePet
import com.example.feedm.petsFeature.domain.EditPet
import com.example.feedm.petsFeature.domain.GetPets
import com.example.feedm.core.domain.model.PetModel
import com.example.feedm.petsFeature.domain.GetPetById
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PetViewModel @Inject constructor(
    private val addPetUseCase: AddPet,
    private val deletePetUseCase: DeletePet,
    private val editPetUseCase: EditPet,
    private val getPetsUseCase: GetPets,
    private val getPetByIdUseCase: GetPetById
) : ViewModel() {

    private val _pets = MutableLiveData<List<PetModel>>(emptyList())
    val pets: LiveData<List<PetModel>> = _pets
    private val _petModel = MutableLiveData<PetModel>()
    val petModel: LiveData<PetModel> = _petModel

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

    fun addPet(petModel: PetModel) {
        viewModelScope.launch {
            addPetUseCase(petModel)
            getData()
        }
    }

    fun getPetById(petId: Int){
        viewModelScope.launch {
           val result = getPetByIdUseCase(petId)
            _petModel.value = result
        }
    }

    fun editPetNotCommitting(petModel: PetModel){
        viewModelScope.launch {
            _petModel.value = petModel
        }
    }

    fun editPet(petModel: PetModel) {
        viewModelScope.launch {
            editPetUseCase(petModel)
            getData()
        }
    }

}