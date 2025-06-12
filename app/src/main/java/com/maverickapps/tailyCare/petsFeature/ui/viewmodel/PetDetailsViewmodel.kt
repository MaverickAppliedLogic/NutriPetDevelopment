package com.maverickapps.tailyCare.petsFeature.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maverickapps.tailyCare.petsFeature.domain.objectTasks.food.model.FoodModel
import com.maverickapps.tailyCare.petsFeature.domain.objectTasks.meal.model.MealModel
import com.maverickapps.tailyCare.petsFeature.domain.objectTasks.meal.useCase.DeleteMealUseCase
import com.maverickapps.tailyCare.petsFeature.domain.objectTasks.pet.model.PetModel
import com.maverickapps.tailyCare.petsFeature.domain.otherTasks.useCase.GetPetsDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PetDetailsViewmodel @Inject constructor(
    private val getPetsDetailsUseCase: GetPetsDetailsUseCase,
    private val deleteMealUseCase: DeleteMealUseCase
): ViewModel() {
    private val _petModel = MutableLiveData<PetModel>()
    private val _meals = MutableLiveData<List<MealModel>>()
    private val _foods = MutableLiveData<List<FoodModel>>()
    private val _calories = MutableLiveData<Double>()
    val petModel: LiveData<PetModel> = _petModel
    val meals: LiveData<List<MealModel>> = _meals
    val foods: LiveData<List<FoodModel>> = _foods
    val calories: LiveData<Double> = _calories




    fun getPetDetails(petId: Int) {
        viewModelScope.launch {
            val result = getPetsDetailsUseCase(petId)
            _petModel.value = result[0] as PetModel
            _meals.value = result[1] as List<MealModel>
            _foods.value = result[2] as List<FoodModel>
            _calories.value = result[3] as Double
        }
    }

    fun deleteMeal(mealModel: MealModel){
        viewModelScope.launch {
            val mealId = listOf(mealModel.mealId)
            deleteMealUseCase(mealId)
            getPetDetails(_petModel.value!!.petId)
        }
    }


}