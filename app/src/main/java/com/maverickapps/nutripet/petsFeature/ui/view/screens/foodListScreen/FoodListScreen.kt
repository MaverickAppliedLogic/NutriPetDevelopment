package com.maverickapps.nutripet.petsFeature.ui.view.screens.foodListScreen

import androidx.compose.runtime.Composable
import com.maverickapps.nutripet.core.ui.theme.NutriPetTheme
import com.maverickapps.nutripet.petsFeature.ui.view.screens.foodListScreen.components.FoodListContent
import com.maverickapps.nutripet.ui.viewmodel.FoodsListViewmodel

@Composable
fun FoodListScreen(
    foodsListViewmodel: FoodsListViewmodel,
    navToAddFood: () -> Unit,
    navToBackStack: (Int?) -> Unit
) {
    NutriPetTheme {
        FoodListContent(
            foodsListViewmodel = foodsListViewmodel,
            navToAddFood = navToAddFood,
            navToBackStack = navToBackStack
        )
    }
}