package com.maverickapps.nutripet.features.pets.ui.view.screens.foodListScreen.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.feedm.R
import com.maverickapps.nutripet.features.pets.ui.view.screens.foodListScreen.components.contentComponents.ContentDisplay
import com.maverickapps.nutripet.ui.viewmodel.FoodsListViewmodel

@Composable
fun FoodListContent(
    foodsListViewmodel: FoodsListViewmodel,
    navToAddFood: () -> Unit,
    navToBackStack: (Int?) -> Unit
) {
    val foodList by foodsListViewmodel.foods.collectAsStateWithLifecycle()
    val formattedFoodList by remember {
        derivedStateOf {
            foodList.map { food ->
                Triple(food.foodId, food.foodName, R.drawable.ic_tailycare)
            }
        }
    }

    var isEditing by remember { mutableStateOf(false) }

    Scaffold(
        contentWindowInsets = WindowInsets.safeDrawing
    ) {
        ContentDisplay(
            foodList = formattedFoodList,
            modifier = Modifier.padding(it),
            isEditing = isEditing,
            onAddButtonClicked = { navToAddFood() },
            onEditButtonClicked = { isEditing = !isEditing },
            onCardClicked = { id->
                println("CardClickedFoodList")
                println(id)
                navToBackStack(id)},
            onIconClicked = { id-> foodsListViewmodel.deleteFood(id)},
            onDropdownClicked = { navToBackStack(null)}
        )
    }
}



