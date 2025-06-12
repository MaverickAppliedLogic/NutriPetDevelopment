package com.maverickapps.tailyCare.petsFeature.ui.view.screens.addMealScreen

import androidx.compose.runtime.Composable
import com.maverickapps.tailyCare.core.ui.theme.TailyCareTheme
import com.maverickapps.tailyCare.petsFeature.ui.view.screens.addMealScreen.components.AddMealContent
import com.maverickapps.tailyCare.petsFeature.ui.viewmodel.AddMealViewmodel

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