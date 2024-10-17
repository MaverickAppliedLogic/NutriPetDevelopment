package com.example.feedm.ui.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.feedm.data.model.PetModel
import com.example.feedm.domain.AddPet
import com.example.feedm.domain.DeletePet
import com.example.feedm.domain.EditPet
import com.example.feedm.domain.GetPet
import com.example.feedm.domain.GetPets
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PetViewModel @Inject constructor(
    private val addPetUseCase: AddPet,
    private val deletePetUseCase: DeletePet,
    private val editPetUseCase: EditPet,
    private val getPetsUseCase: GetPets,
    private val getPetUseCase: GetPet
): ViewModel(){

    private val _pets = MutableLiveData<ArrayList<PetModel>>()
    val pets: LiveData<ArrayList<PetModel>> = _pets
    val pet = MutableLiveData<PetModel>()

    @SuppressLint("NullSafeMutableLiveData")
    fun onCreate() {
        val result = getPetsUseCase()
        if (!result.isNullOrEmpty()) {
            _pets.value = result
        }
    }

    @SuppressLint("NullSafeMutableLiveData")
    fun deletePet(pet: PetModel){
        deletePetUseCase(pet)
        val result = getPetsUseCase()
        if (!result.isNullOrEmpty()) {
            _pets.postValue(result)
        }
    }

    @SuppressLint("NullSafeMutableLiveData")
    fun addpet(pet: PetModel){
        addPetUseCase(pet)
        val result = getPetsUseCase()
        if (!result.isNullOrEmpty()) {
            _pets.postValue(result)
        }
    }

    @SuppressLint("NullSafeMutableLiveData")
    fun getPet(pos: Int){
        val result = getPetUseCase(pos)
        pet.postValue(result)
    }

    @SuppressLint("NullSafeMutableLiveData")
    fun editPet(pet: PetModel,pos: Int){
        editPetUseCase(pet,pos)
        val result = getPetsUseCase()
        if (!result.isNullOrEmpty()) {
            _pets.postValue(result)
        }
    }
}