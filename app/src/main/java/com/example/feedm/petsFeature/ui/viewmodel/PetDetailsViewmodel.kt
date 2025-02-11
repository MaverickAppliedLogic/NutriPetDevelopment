package com.example.feedm.petsFeature.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feedm.petsFeature.domain.objectTasks.meal.model.MealModel
import com.example.feedm.petsFeature.domain.objectTasks.meal.useCase.GetMealsUseCase
import com.example.feedm.petsFeature.domain.objectTasks.pet.model.PetModel
import com.example.feedm.petsFeature.domain.objectTasks.pet.useCase.GetPetByIdUseCase
import com.example.feedm.petsFeature.domain.otherTasks.useCase.CalculateCaloriesUseCase
import com.example.feedm.petsFeature.domain.otherTasks.useCase.GetPetsDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PetDetailsViewmodel @Inject constructor(
    private val getPetsDetailsUseCase: GetPetsDetailsUseCase
): ViewModel() {
    private val _petModel = MutableLiveData<PetModel>()
    private val _meals = MutableLiveData<List<MealModel>>()
    private val _calories = MutableLiveData<Double>()
    val petModel: LiveData<PetModel> = _petModel
    val meals: LiveData<List<MealModel>> = _meals
    val calories: LiveData<Double> = _calories

    fun getPetDetails(petId: Int) {
        viewModelScope.launch {
            val result = getPetsDetailsUseCase(petId)
            _petModel.value = result[0] as PetModel
            _meals.value = result[1] as List<MealModel>
            _calories.value = result[2] as Double
        }
    }


}