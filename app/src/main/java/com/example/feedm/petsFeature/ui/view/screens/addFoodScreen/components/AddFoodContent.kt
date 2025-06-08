package com.example.feedm.petsFeature.ui.view.screens.addFoodScreen.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.feedm.petsFeature.domain.objectTasks.food.model.FoodModel
import com.example.feedm.petsFeature.ui.view.screens.addFoodScreen.components.contentComponents.FoodFields
import com.example.feedm.petsFeature.ui.viewmodel.AddFoodViewModel
import java.util.Locale

@Composable
fun AddFoodContent(
    addFoodViewModel: AddFoodViewModel = viewModel(),
    navToBackStack: () -> Unit,
    navToFoodList: () -> Unit
) {
    val food by addFoodViewModel.foodToBeAdded.collectAsStateWithLifecycle()
    var foodIsValid by remember { mutableStateOf(true) }
    var foodCalories by remember {
        mutableStateOf(
            String.format(Locale.getDefault(), "%.0f", food.calories)
        )
    }
    FoodFields(
        foodName = food.foodName,
        foodCalories = foodCalories,
        foodIsValid = foodIsValid,
        onFoodNameChanged = { newFoodName ->
            foodIsValid = true
            addFoodViewModel.foodChanged(food.copy(foodName = newFoodName))},
        onFoodCaloriesChanged = { newCalories ->
            foodIsValid = true
            foodCalories = newCalories
            if (newCalories.isNotEmpty()) {
                addFoodViewModel.foodChanged(
                    food.copy(calories = newCalories.toFloat()))
            }
            else {
                addFoodViewModel.foodChanged(food.copy(calories = 0f))
            }
            },
        onCommitButtonClicked = {
            foodIsValid = validateFood(food)
            if (foodIsValid) {
                addFoodViewModel.addFood()
                navToFoodList()
            }
        },
        onCloseIconClicked = { navToBackStack() },
    )
}

fun validateFood(foodModel: FoodModel): Boolean {
    return foodModel.foodName.isNotEmpty() && foodModel.calories > 0
}



