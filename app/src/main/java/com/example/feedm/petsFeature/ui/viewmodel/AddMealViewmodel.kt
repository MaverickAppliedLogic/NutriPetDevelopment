package com.example.feedm.petsFeature.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feedm.petsFeature.domain.objectTasks.food.model.FoodModel
import com.example.feedm.petsFeature.domain.objectTasks.food.useCase.DeleteFoodUseCase
import com.example.feedm.petsFeature.domain.objectTasks.meal.model.MealModel
import com.example.feedm.petsFeature.domain.objectTasks.meal.useCase.AddMealUseCase
import com.example.feedm.petsFeature.domain.objectTasks.petFood.useCase.GetFoodsByPetIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddMealViewmodel @Inject constructor(
    private val addMealUseCase: AddMealUseCase,
    private val getFoodsByPetIdUseCase: GetFoodsByPetIdUseCase,
    private val deleteFoodUseCase: DeleteFoodUseCase
    ): ViewModel() {

    private val _petId = MutableLiveData<Int>()
    private val _foods = MutableLiveData<List<FoodModel>>()
    val foods: LiveData<List<FoodModel>> = _foods
    private val _foodToBeDeleted = MutableLiveData<FoodModel>()
    val foodToBeDeleted: LiveData<FoodModel> = _foodToBeDeleted


    fun getFoodsByPetId(petId: Int) {
        viewModelScope.launch {
            _petId.value = petId
            val foodsList = getFoodsByPetIdUseCase(petId)
            _foods.value = foodsList
        }
    }

    fun getFoodToBeDeleted(foodSelected: String){
        viewModelScope.launch {
            for(food in _foods.value!!){
                if(food.foodName.equals(foodSelected)){
                _foodToBeDeleted.value = food
                }
            }
        }
    }


    fun addMeal(mealModel: MealModel?,
                ration: Float?,
                hour: Int?,
                min: Int?,
                mealCalories: Double?,
                food: FoodModel,
                petId: Int?){
        viewModelScope.launch {
        addMealUseCase(
            mealModel = mealModel,
            ration = ration, hour = hour, min = min,
            mealCalories =  mealCalories, food = food, petId =  petId)
        }
    }

    fun deleteFood(foodModel: FoodModel){
        viewModelScope.launch {
            deleteFoodUseCase(foodModel)
            _foods.value = getFoodsByPetIdUseCase(_petId.value!!)
        }
    }

}