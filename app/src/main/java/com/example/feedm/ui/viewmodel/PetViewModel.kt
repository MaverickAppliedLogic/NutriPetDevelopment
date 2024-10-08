package com.example.feedm.ui.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.feedm.data.model.PetModel
import com.example.feedm.domain.GetPets

class PetViewModel: ViewModel(){

    val pets = MutableLiveData<List<PetModel>>()


    @SuppressLint("NullSafeMutableLiveData")
    fun onCreate(context: Context) {
        val getPetsUseCase = GetPets(context)
        val result = getPetsUseCase()
        if (!result.isNullOrEmpty()) {
            pets.value = result
        }
    }
}