package com.example.feedm.petsFeature.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.feedm.petsFeature.domain.model.PetModel
import com.example.feedm.petsFeature.domain.CalculateCaloriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor(
    private val calculateCaloriesUseCase: CalculateCaloriesUseCase
): ViewModel()
{
    private val _calories = MutableLiveData<Double>()
    val calories: MutableLiveData<Double> = _calories

    fun calculateCalories(pet: PetModel): Double {
        return calculateCaloriesUseCase(pet)
    }

}