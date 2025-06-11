package com.example.feedm.petsFeature.ui.view.screens.addFoodScreen.components.contentComponents.foodFieldsOrientations

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.feedm.core.ui.theme.Neutral
import com.example.feedm.petsFeature.ui.view.screens.addFoodScreen.components.contentComponents.foodFormOrientations.FoodFormPLandscape

@Composable
fun LandscapeFields(
    foodIsValid: Boolean,
    foodName: String,
    foodCalories: String,
    onFoodNameChanged: (String) -> Unit,
    onFoodCaloriesChanged: (String) -> Unit,
    onCommitButtonClicked: () -> Unit = {},
    onCloseIconClicked: () -> Unit = {}
) {
    Scaffold {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(Neutral),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FoodFormPLandscape(
                modifier = Modifier.weight(0.97f),
                foodIsValid = foodIsValid,
                foodName = foodName,
                foodCalories = foodCalories,
                onFoodNameChanged = { newFoodName -> onFoodNameChanged(newFoodName) },
                onCommitButtonClicked = { onCommitButtonClicked() },
                onFoodCaloriesChanged = { newCalories -> onFoodCaloriesChanged(newCalories) },
                onCloseIconClicked = { onCloseIconClicked() }
            )
            Spacer(modifier = Modifier.weight(0.03f))
        }
    }
}