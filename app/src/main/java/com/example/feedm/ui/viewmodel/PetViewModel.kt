package com.example.feedm.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.feedm.data.model.PetModel
import com.example.feedm.domain.GetPets

class PetViewModel(context: Context): ViewModel(){

    val pets = MutableLiveData<List<PetModel>>()
    val getPetsUseCase = GetPets(context)

    fun onCreate(){
        pets.value = getPetsUseCase.invoke()
    }
}