package com.example.feedm.petsFeature.ui.view.screens.foodListScreen

import androidx.compose.runtime.Composable
import com.example.feedm.core.ui.theme.TailyCareTheme
import com.example.feedm.petsFeature.ui.view.screens.foodListScreen.components.FoodListContent
import com.example.feedm.ui.viewmodel.FoodsListViewmodel

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