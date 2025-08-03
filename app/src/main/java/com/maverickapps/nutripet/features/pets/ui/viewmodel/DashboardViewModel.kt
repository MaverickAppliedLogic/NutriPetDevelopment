package com.maverickapps.nutripet.features.pets.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maverickapps.nutripet.features.pets.domain.objectTasks.food.model.FoodModel
import com.maverickapps.nutripet.features.pets.domain.objectTasks.food.useCase.GetFoodUseCase
import com.maverickapps.nutripet.features.pets.domain.objectTasks.meal.model.MealModel
import com.maverickapps.nutripet.features.pets.domain.objectTasks.meal.useCase.DeleteMealUseCase
import com.maverickapps.nutripet.features.pets.domain.objectTasks.meal.useCase.EditMealUseCase
import com.maverickapps.nutripet.features.pets.domain.objectTasks.meal.useCase.GetMealsUseCase
import com.maverickapps.nutripet.features.pets.domain.objectTasks.pet.model.PetModel
import com.maverickapps.nutripet.features.pets.domain.objectTasks.pet.useCase.DeletePetUseCase
import com.maverickapps.nutripet.features.pets.domain.objectTasks.pet.useCase.GetPetsUseCase
import com.maverickapps.nutripet.features.pets.domain.otherTasks.useCase.CalculateCaloriesUseCase
import com.maverickapps.nutripet.features.streak.domain.usecases.FetchStreakUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getPetsUseCase: GetPetsUseCase,
    private val deletePetUseCase: DeletePetUseCase,
    private val getMealsUseCase: GetMealsUseCase,
    private val editMealUseCase: EditMealUseCase,
    private val getFoodsUseCase: GetFoodUseCase,
    private val calculateCaloriesUseCase: CalculateCaloriesUseCase,
    private val deleteMealUseCase: DeleteMealUseCase,
    private val fetchStreakUseCase: FetchStreakUseCase
) : ViewModel() {

    var selectedPetId = MutableStateFlow<Int?>(null)
    var requiredCalories = MutableStateFlow<Int?>(null)

    private val _pets = MutableStateFlow<List<PetModel>>(emptyList())
    val pets: StateFlow<List<PetModel>> = _pets

    private val _mealsWithFoods = MutableStateFlow<List<Pair<MealModel, FoodModel?>>>(emptyList())
    val mealsWithFoods: StateFlow<List<Pair<MealModel, FoodModel?>>> = _mealsWithFoods


    init {
        fetchData()
    }

    fun fetchData() {
        viewModelScope.launch {
            Log.d("DashboardViewModel", "fetchData: Fetching data...")
            _pets.value = getPetsUseCase()
            setPetId(_pets.value.firstOrNull()?.petId)
            if (selectedPetId.value != null) {
                getMeals()

            }
        }
    }

    fun setPetId(petId: Int?) {
            selectedPetId.value = petId
            val pet = _pets.value.find { it.petId == petId }
        if (pet != null){
            calculateRequiredCalories(pet)
        }

    }

    private fun calculateRequiredCalories(pet: PetModel) {
        requiredCalories.value = calculateCaloriesUseCase(pet).toInt()
    }

    fun deletePet(petId: Int) {
        viewModelScope.launch {
            deletePetUseCase(petId)
            fetchData()
        }
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


    fun deleteMeal(meal: MealModel) {
        viewModelScope.launch {
            deleteMealUseCase(meal)
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
