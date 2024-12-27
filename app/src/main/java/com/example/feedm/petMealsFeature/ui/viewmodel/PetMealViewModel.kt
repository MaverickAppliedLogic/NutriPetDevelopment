package com.example.feedm.petMealsFeature.ui.viewmodel

import androidx.collection.emptyObjectList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feedm.petMealsFeature.domain.GetPetMeals
import com.example.feedm.petMealsFeature.domain.model.PetMealsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Objects
import javax.inject.Inject


@HiltViewModel
class PetMealViewModel @Inject constructor(
    private val getPetMealsUseCase: GetPetMeals,
) : ViewModel() {

    private val _petMeals = MutableLiveData<PetMealsModel>()
    val petMeals: LiveData<PetMealsModel> = _petMeals

    fun getPetMeals(petId: Int){
        viewModelScope.launch {
            val result = getPetMealsUseCase(petId)
            _petMeals.value = result
        }
    }


}