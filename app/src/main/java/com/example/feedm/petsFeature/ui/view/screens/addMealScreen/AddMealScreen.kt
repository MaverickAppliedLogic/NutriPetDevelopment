package com.example.feedm.petsFeature.ui.view.screens.addMealScreen

import androidx.compose.runtime.Composable
import com.example.feedm.core.ui.theme.TailyCareTheme
import com.example.feedm.petsFeature.ui.view.screens.addMealScreen.components.AddMealContent
import com.example.feedm.petsFeature.ui.viewmodel.AddMealViewmodel

@Composable
fun AddMealScreen(
    addMealViewmodel: AddMealViewmodel,
    mealId: Int,
    petId: Int,
    foodId: Int,
    navToFoodList: () -> Unit,
    navToBackStack: () -> Unit
){
    TailyCareTheme {
        AddMealContent(
            addMealViewmodel = addMealViewmodel,
            mealId = mealId,
            petId = petId,
            foodId = foodId,
            navToFoodList = navToFoodList,
            navToBackStack = navToBackStack
        )
    }
}