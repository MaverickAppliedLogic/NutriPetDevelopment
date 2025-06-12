package com.maverickapps.tailyCare.petsFeature.ui.view.screens.addFoodScreen.components.contentComponents

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import com.maverickapps.tailyCare.core.ui.theme.ScreenHeight
import com.maverickapps.tailyCare.core.ui.theme.ScreenOrientation
import com.maverickapps.tailyCare.petsFeature.ui.view.screens.addFoodScreen.components.contentComponents.foodFieldsOrientations.LandscapeFields
import com.maverickapps.tailyCare.petsFeature.ui.view.screens.addFoodScreen.components.contentComponents.foodFieldsOrientations.PortraitFields

@Composable
fun FoodFields(
    foodIsValid: Boolean,
    foodName: String,
    foodCalories: String,
    onFoodNameChanged: (String) -> Unit,
    onFoodCaloriesChanged: (String) -> Unit,
    onCommitButtonClicked: () -> Unit = {},
    onCloseIconClicked: () -> Unit = {}
) {
    if (!(ScreenOrientation == Configuration.ORIENTATION_LANDSCAPE || ScreenHeight < 600)) {
        PortraitFields(
            foodIsValid = foodIsValid,
            foodName = foodName,
            foodCalories = foodCalories,
            onFoodNameChanged = { onFoodNameChanged(it) },
            onFoodCaloriesChanged = { onFoodCaloriesChanged(it) },
            onCommitButtonClicked = { onCommitButtonClicked() },
            onCloseIconClicked = { onCloseIconClicked() })
    } else {
        LandscapeFields(
            foodIsValid = foodIsValid,
            foodName = foodName,
            foodCalories = foodCalories,
            onFoodNameChanged = { onFoodNameChanged(it) },
            onFoodCaloriesChanged = { onFoodCaloriesChanged(it) },
            onCommitButtonClicked = { onCommitButtonClicked() },
            onCloseIconClicked = { onCloseIconClicked() })
    }

}



