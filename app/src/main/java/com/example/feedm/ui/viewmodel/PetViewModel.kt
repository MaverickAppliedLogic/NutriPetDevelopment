package com.example.feedm.ui.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feedm.domain.AddPet
import com.example.feedm.domain.DeletePet
import com.example.feedm.domain.EditPet
import com.example.feedm.domain.GetPets
import com.example.feedm.domain.model.Pet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PetViewModel @Inject constructor(
    private val addPetUseCase: AddPet,
    private val deletePetUseCase: DeletePet,
    private val editPetUseCase: EditPet,
    private val getPetsUseCase: GetPets
) : ViewModel() {

    private val _pets = MutableLiveData<List<Pet>>(emptyList())
    val pets: LiveData<List<Pet>> = _pets
    private val _pet = MutableLiveData<Pet>()
    val pet: LiveData<Pet> = _pet


    @SuppressLint("NullSafeMutableLiveData")
    fun onCreate() {
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


    fun deletePet(pet: Pet) {
        viewModelScope.launch {
            deletePetUseCase(pet)
            onCreate()
        }
    }

    fun addPet(pet: Pet) {
        viewModelScope.launch {
            addPetUseCase(pet)
            onCreate()
        }
    }

    fun editPet(pet: Pet) {
        viewModelScope.launch {
            editPetUseCase(pet)
            onCreate()
        }
    }

    fun getPet(id: Int) {
        viewModelScope.launch {
            val pets = _pets.value
            if (pets.isNullOrEmpty()) {
                return@launch
            }
            _pet.value = pets.firstOrNull { it.id == id }
                ?: run { null }
        }
    }
}