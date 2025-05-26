package com.example.feedm.petsFeature.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feedm.petsFeature.domain.objectTasks.meal.model.MealModel
import com.example.feedm.petsFeature.domain.objectTasks.meal.useCase.AddMealUseCase
import com.example.feedm.petsFeature.domain.objectTasks.meal.useCase.DeleteMealUseCase
import com.example.feedm.petsFeature.domain.objectTasks.meal.useCase.GetMealsUseCase
import com.example.feedm.petsFeature.domain.objectTasks.meal.useCase.UpdateMealsDayChangedUseCase
import com.example.feedm.petsFeature.domain.objectTasks.pet.model.PetModel
import com.example.feedm.petsFeature.domain.objectTasks.pet.useCase.GetPetsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getPetsUseCase: GetPetsUseCase,
    private val getMealsUseCase: GetMealsUseCase,
    private val addMealUseCase: AddMealUseCase,
    private val deleteMealUseCase: DeleteMealUseCase,
    private val updateMealsDayChangedUseCase: UpdateMealsDayChangedUseCase
) : ViewModel() {
    private val _pets = MutableStateFlow<List<PetModel>>(emptyList())
    val pets: StateFlow<List<PetModel>> = _pets

    private val _meals = MutableStateFlow<List<MealModel>>(emptyList())
    val meals: StateFlow<List<MealModel>> = _meals

    fun init() {
        viewModelScope.launch {
            _pets.value = getPetsUseCase()
            // Si hay al menos una mascota, obtener sus comidas
            if (_pets.value.isNotEmpty()) {
                val allMeals = mutableListOf<MealModel>()
                _pets.value.forEach { pet ->
                    allMeals += getMealsUseCase(pet.petId)
                }
                _meals.value = allMeals
            } else {
                _meals.value = emptyList()
            }
        }
    }

    fun deleteMeal(mealId: Int) {
        viewModelScope.launch {
            deleteMealUseCase(listOf(mealId))
            // Refrescar comidas después de eliminar
            init()
        }
    }

    fun editMeal(meal: MealModel) {
        viewModelScope.launch {
            addMealUseCase(meal)
            // Refrescar comidas después de editar
            init()
        }
    }
}
