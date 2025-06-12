package com.maverickapps.tailyCare.petsFeature.ui.view.screens.foodListScreen

import androidx.compose.runtime.Composable
import com.maverickapps.tailyCare.core.ui.theme.TailyCareTheme
import com.maverickapps.tailyCare.petsFeature.ui.view.screens.foodListScreen.components.FoodListContent
import com.maverickapps.tailyCare.ui.viewmodel.FoodsListViewmodel

@Composable
fun FoodListScreen(
    foodsListViewmodel: FoodsListViewmodel,
    navToAddFood: () -> Unit,
    navToBackStack: (Int?) -> Unit
) {
    TailyCareTheme {
        FoodListContent(
            foodsListViewmodel = foodsListViewmodel,
            navToAddFood = navToAddFood,
            navToBackStack = navToBackStack
        )
    }
}