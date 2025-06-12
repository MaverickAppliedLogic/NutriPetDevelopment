package com.maverickapps.tailyCare.petsFeature.ui.view.screens.addFoodScreen

import androidx.compose.runtime.Composable
import com.maverickapps.tailyCare.core.ui.theme.TailyCareTheme
import com.maverickapps.tailyCare.petsFeature.ui.view.screens.addFoodScreen.components.AddFoodContent
import com.maverickapps.tailyCare.petsFeature.ui.viewmodel.AddFoodViewModel

@Composable
fun AddFoodScreen (
    addFoodViewModel: AddFoodViewModel,
    navToBackStack: () -> Unit,
    navToFoodList: () -> Unit
){
    TailyCareTheme {
        AddFoodContent(
            addFoodViewModel = addFoodViewModel,
            navToBackStack = navToBackStack,
            navToFoodList = navToFoodList
        )
    }
}