package com.maverickapps.nutripet.petsFeature.ui.view.screens.addMealScreen.components

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
import com.maverickapps.nutripet.petsFeature.ui.view.screens.addMealScreen.components.contentComponents.AddMealFields
import com.maverickapps.nutripet.petsFeature.ui.viewmodel.AddMealViewmodel
import java.util.Locale


@Composable
fun AddMealContent(
    addMealViewmodel: AddMealViewmodel,
    mealId: Int?,
    petId: Int,
    foodId: Int,
    navToFoodList: () -> Unit,
    navToBackStack: () -> Unit
) {
    val mealToBeAdded by addMealViewmodel.mealToBeAdded.collectAsStateWithLifecycle()
    val foodSelected by addMealViewmodel.foodSelected.collectAsStateWithLifecycle()
    val mealIsValid by addMealViewmodel.mealIsValid.collectAsStateWithLifecycle()
    var rationFormatted by remember(mealToBeAdded.ration) {
        if (mealToBeAdded.ration == 0f) mutableStateOf("")
        else mutableStateOf(String.format(Locale.getDefault(), "%.0f", mealToBeAdded.ration))

    }
    addMealViewmodel.mealToBeAddedChanged(mealToBeAdded.copy(petId = petId))
    LaunchedEffect(mealId) {
        if (mealId != null){
            addMealViewmodel.getMeal(mealId)
        }
    }

    LaunchedEffect(foodId) {
        if (foodId != -1) addMealViewmodel.getFood(foodId)
    }
    Scaffold {
        AddMealFields(
            mealIsValid = mealIsValid,
            rationFormatted = rationFormatted,
            mealTime = mealToBeAdded.mealTime,
            food = foodSelected,
            navToFoodList = {
                navToFoodList()
            },
            addButtonClicked = {makeItDaily ->
                addMealViewmodel.mealToBeAddedChanged(
                    mealToBeAdded.copy(
                        isDailyMeal = makeItDaily
                    )
                )
                addMealViewmodel.addMeal()
                navToBackStack()
            },
            onRationChanged = { newRation ->
                rationFormatted = newRation
                addMealViewmodel.mealToBeAddedChanged(
                    mealToBeAdded.copy(
                        ration = newRation.toFloatOrNull() ?: 0f
                    )
                )
                              },
            onMealTimeChanged = { newMealTime ->
                addMealViewmodel.mealToBeAddedChanged(mealToBeAdded.copy(mealTime = newMealTime))
            },
            modifier = Modifier.padding(it)
        )
    }
}







