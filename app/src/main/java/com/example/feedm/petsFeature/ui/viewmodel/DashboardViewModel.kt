package com.example.feedm.petsFeature.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feedm.petsFeature.domain.objectTasks.food.model.FoodModel
import com.example.feedm.petsFeature.domain.objectTasks.food.useCase.GetFoodUseCase
import com.example.feedm.petsFeature.domain.objectTasks.meal.model.MealModel
import com.example.feedm.petsFeature.domain.objectTasks.meal.useCase.DeleteMealUseCase
import com.example.feedm.petsFeature.domain.objectTasks.meal.useCase.EditMealUseCase
import com.example.feedm.petsFeature.domain.objectTasks.meal.useCase.GetMealsUseCase
import com.example.feedm.petsFeature.domain.objectTasks.pet.model.PetModel
import com.example.feedm.petsFeature.domain.objectTasks.pet.useCase.GetPetsUseCase
import com.example.feedm.petsFeature.domain.otherTasks.useCase.CalculateCaloriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getPetsUseCase: GetPetsUseCase,
    private val getMealsUseCase: GetMealsUseCase,
    private val editMealUseCase: EditMealUseCase,
    private val getFoodsUseCase: GetFoodUseCase,
    private val calculateCaloriesUseCase: CalculateCaloriesUseCase,
    private val deleteMealUseCase: DeleteMealUseCase,
) : ViewModel() {

    var selectedPetId = MutableStateFlow<Int?>(null)
    var requiredCalories = MutableStateFlow<Int?>(null)

    private val _pets = MutableStateFlow<List<PetModel>>(emptyList())
    val pets: StateFlow<List<PetModel>> = _pets

    private val _mealsWithFoods = MutableStateFlow<List<Pair<MealModel,FoodModel?>>>(emptyList())
    val mealsWithFoods: StateFlow<List<Pair<MealModel,FoodModel?>>> = _mealsWithFoods

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            _pets.value = getPetsUseCase()
        }
    }

    fun setPetId(petId: Int) {
        selectedPetId.value = petId
        requiredCalories.value =
            calculateCaloriesUseCase(_pets.value.find { it.petId == petId }!!).toInt()
    }
    fun getMeals() {
        viewModelScope.launch {
            val meals = getMealsUseCase(selectedPetId.value!!)
            getFoods(meals)
        }
    }

    private fun getFoods(meals: List<MealModel>) {
        viewModelScope.launch {
            val mealsAndFoods = mutableListOf<Pair<MealModel, FoodModel?>>()
            meals.forEach {
                if (it.foodId != null) {
                    mealsAndFoods.add(Pair(it, getFoodsUseCase(it.foodId)))
                }
                else {
                    mealsAndFoods.add(Pair(it, null))
                }
            }
            _mealsWithFoods.value = mealsAndFoods
        }
    }


    fun deleteMeal(mealId: Int) {
        viewModelScope.launch {
            deleteMealUseCase(listOf(mealId))
            getMeals()
        }
    }

    fun editMeal(meal: MealModel) {
        viewModelScope.launch {
            editMealUseCase(meal)
            getMeals()
        }
    }
}
