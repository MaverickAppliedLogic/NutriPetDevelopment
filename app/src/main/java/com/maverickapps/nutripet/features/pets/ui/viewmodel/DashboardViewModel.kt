package com.maverickapps.nutripet.features.pets.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maverickapps.nutripet.features.pets.domain.objectTasks.food.model.FoodModel
import com.maverickapps.nutripet.features.pets.domain.objectTasks.food.useCase.GetFoodUseCase
import com.maverickapps.nutripet.features.pets.domain.objectTasks.meal.model.MealModel
import com.maverickapps.nutripet.features.pets.domain.objectTasks.meal.useCase.DeleteMealUseCase
import com.maverickapps.nutripet.features.pets.domain.objectTasks.meal.useCase.EditMealUseCase
import com.maverickapps.nutripet.features.pets.domain.objectTasks.meal.useCase.GetMealsByPetIdUseCase
import com.maverickapps.nutripet.features.pets.domain.objectTasks.pet.model.PetModel
import com.maverickapps.nutripet.features.pets.domain.objectTasks.pet.useCase.DeletePetUseCase
import com.maverickapps.nutripet.features.pets.domain.objectTasks.pet.useCase.GetPetsUseCase
import com.maverickapps.nutripet.features.pets.domain.otherTasks.useCase.CalculateCaloriesUseCase
import com.maverickapps.nutripet.features.streak.domain.model.Streak
import com.maverickapps.nutripet.features.streak.domain.usecases.FetchStreakUseCase
import com.maverickapps.nutripet.features.streak.domain.usecases.GetStreakUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getPetsUseCase: GetPetsUseCase,
    private val deletePetUseCase: DeletePetUseCase,
    private val getMealsByPetIdUseCase: GetMealsByPetIdUseCase,
    private val editMealUseCase: EditMealUseCase,
    private val getFoodsUseCase: GetFoodUseCase,
    private val calculateCaloriesUseCase: CalculateCaloriesUseCase,
    private val deleteMealUseCase: DeleteMealUseCase,
    private val getStreakUseCase: GetStreakUseCase,
    private val fetchStreakUseCase: FetchStreakUseCase
) : ViewModel() {

    private val _showSplashScreen = MutableStateFlow(true)
    val showSplashScreen: StateFlow<Boolean> = _showSplashScreen

    var selectedPetId = MutableStateFlow<Int?>(null)

    private val _requiredCalories = MutableStateFlow<Int?>(null)
    val requiredCalories : StateFlow<Int?> = _requiredCalories

    private val _pets = MutableStateFlow<List<PetModel>>(emptyList())
    val pets: StateFlow<List<PetModel>> = _pets

    private val _mealsWithFoods = MutableStateFlow<List<Pair<MealModel, FoodModel?>>>(emptyList())
    val mealsWithFoods: StateFlow<List<Pair<MealModel, FoodModel?>>> = _mealsWithFoods

    private val _streak = MutableStateFlow(getStreakUseCase())
    val streak: StateFlow<Streak> = _streak


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
            getStreak()
            println("Streak: ${_streak.value}")
            hideSplashScreen()
        }
    }

    private fun hideSplashScreen() {
        _showSplashScreen.value = false
    }

    fun setPetId(petId: Int?) {
            selectedPetId.value = petId
            val pet = _pets.value.find { it.petId == petId }
        if (pet != null){
            calculateRequiredCalories(pet)
        }

    }

    private fun calculateRequiredCalories(pet: PetModel) {
        _requiredCalories.value = calculateCaloriesUseCase(pet).toInt()
    }

    fun deletePet(petId: Int) {
        viewModelScope.launch {
            deletePetUseCase(petId)
            fetchData()
        }
    }
    fun getMeals() {
        viewModelScope.launch {
            val meals = getMealsByPetIdUseCase(selectedPetId.value!!)
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
        println("State: ${meal.mealState}")

        viewModelScope.launch {
            if (meal.mealState == 0){
                println("Meal state XD: ${meal.mealState}")
                fetchStreak(_streak.value.copy(alreadyFetched = true))
            }
            editMealUseCase(meal)
            getMeals()
        }
    }

    private fun getStreak(){
        _streak.value = getStreakUseCase()

    }

    private fun fetchStreak(streak: Streak){
        println("Streak viewmodel $streak")
        fetchStreakUseCase(streak)
        getStreak()
    }
}
