package com.maverickapps.nutripet.features.pets.ui.view.screens.addMealScreen

import androidx.compose.runtime.Composable
import com.maverickapps.nutripet.core.ui.theme.NutriPetTheme
import com.maverickapps.nutripet.features.pets.ui.view.screens.addMealScreen.components.AddMealContent
import com.maverickapps.nutripet.features.pets.ui.viewmodel.AddMealViewmodel

@Composable
fun AddMealScreen(
    addMealViewmodel: AddMealViewmodel,
    mealId: Int?,
    petId: Int,
    foodId: Int?,
    navToFoodList: () -> Unit,
    navToBackStack: () -> Unit
){
    NutriPetTheme {
        AddMealContent(
            addMealViewmodel = addMealViewmodel,
            mealId = mealId,
            petId = petId,
            foodId = foodId?:-1,
            navToFoodList = navToFoodList,
            navToBackStack = navToBackStack
        )
    }
}