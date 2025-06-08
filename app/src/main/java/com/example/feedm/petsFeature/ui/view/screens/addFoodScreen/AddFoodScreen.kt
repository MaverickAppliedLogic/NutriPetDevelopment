package com.example.feedm.petsFeature.ui.view.screens.addFoodScreen

import androidx.compose.runtime.Composable
import com.example.feedm.core.ui.theme.TailyCareTheme
import com.example.feedm.petsFeature.ui.view.screens.addFoodScreen.components.AddFoodContent
import com.example.feedm.petsFeature.ui.viewmodel.AddFoodViewModel

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