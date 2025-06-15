package com.maverickapps.nutripet.petsFeature.ui.view.screens.addFoodScreen

import androidx.compose.runtime.Composable
import com.maverickapps.nutripet.core.ui.theme.NutriPetTheme
import com.maverickapps.nutripet.petsFeature.ui.view.screens.addFoodScreen.components.AddFoodContent
import com.maverickapps.nutripet.petsFeature.ui.viewmodel.AddFoodViewModel

@Composable
fun AddFoodScreen (
    addFoodViewModel: AddFoodViewModel,
    navToBackStack: () -> Unit,
    navToFoodList: () -> Unit
){
    NutriPetTheme {
        AddFoodContent(
            addFoodViewModel = addFoodViewModel,
            navToBackStack = navToBackStack,
            navToFoodList = navToFoodList
        )
    }
}