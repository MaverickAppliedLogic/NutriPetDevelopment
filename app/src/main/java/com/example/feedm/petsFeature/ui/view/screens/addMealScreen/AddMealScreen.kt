package com.example.feedm.petsFeature.ui.view.screens.addMealScreen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.feedm.petsFeature.ui.view.screens.addMealScreen.components.AddMealContent
import com.example.feedm.petsFeature.ui.viewmodel.AddMealViewmodel
import java.util.Locale


@Composable
fun AddMealScreen(
    addMealViewmodel: AddMealViewmodel,
    petId: Int,
    foodId: Int,
    navToFoodList: () -> Unit,
    navToBackStack: () -> Unit
) {
    val mealToBeAdded by addMealViewmodel.mealToBeAdded.collectAsStateWithLifecycle()
    val foodSelected by addMealViewmodel.foodSelected.collectAsStateWithLifecycle()
    var rationFormatted by remember {
        mutableStateOf(String.format(Locale.getDefault(), "%.0f", mealToBeAdded.ration))
    }
    addMealViewmodel.mealToBeAddedChanged(mealToBeAdded.copy(petId = petId))

    LaunchedEffect(foodId) {
        if (foodId != -1) addMealViewmodel.getFood(foodId)
    }
    Scaffold {
        AddMealContent(
            rationFormatted = rationFormatted,
            mealTime = mealToBeAdded.mealTime,
            food = foodSelected,
            navToFoodList = {
                println("NavToFoodListAddMeal")
                println(mealToBeAdded.petId)
                navToFoodList() },
            addButtonClicked = {
                addMealViewmodel.mealToBeAddedChanged(mealToBeAdded.copy(
                        ration = rationFormatted.toFloatOrNull()?:0f))
                println(mealToBeAdded.foodId)
                println(mealToBeAdded.petId)
                addMealViewmodel.addMeal()
                navToBackStack() },
            onRationChanged = {newRation -> rationFormatted = newRation },
            onMealTimeChanged = {newMealTime ->
                addMealViewmodel.mealToBeAddedChanged(mealToBeAdded.copy(mealTime = newMealTime)) },
            modifier = Modifier.padding(it)
        )
    }
}







