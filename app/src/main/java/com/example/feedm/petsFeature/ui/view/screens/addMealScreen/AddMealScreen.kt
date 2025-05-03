package com.example.feedm.petsFeature.ui.view.screens.addMealScreen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.feedm.core.ui.theme.SecondaryDarkest
import com.example.feedm.petsFeature.domain.objectTasks.food.model.FoodModel
import com.example.feedm.petsFeature.ui.view.screens.addMealScreen.components.MealData
import com.example.feedm.petsFeature.ui.view.screens.addMealScreen.components.MealTimePicker
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
    var mealTime by remember {
        mutableLongStateOf(mealToBeAdded.mealTime)
    }

    LaunchedEffect(true) {
        addMealViewmodel.mealToBeAddedChanged(mealToBeAdded.copy(petId = petId))
    }
    LaunchedEffect(foodId) {
        if (foodId != -1) addMealViewmodel.getFood(foodId)
    }
    Scaffold {
        AddMealContent(
            rationFormatted = rationFormatted,
            mealTime = mealTime,
            food = foodSelected,
            navToFoodList = { navToFoodList() },
            addButtonClicked = {
                addMealViewmodel.mealToBeAddedChanged(
                    mealToBeAdded.copy(
                        ration = rationFormatted.toFloatOrNull()?:0f))
                navToBackStack() },
            onRationChanged = {newRation -> rationFormatted = newRation },
            onMealTimeChanged = {newMealTime -> mealTime = newMealTime },
            modifier = Modifier.padding(it)
        )
    }
}

@Composable
fun AddMealContent(
    rationFormatted: String,
    mealTime: Long,
    food: FoodModel,
    navToFoodList: () -> Unit,
    addButtonClicked: () -> Unit,
    onRationChanged: (String) -> Unit,
    onMealTimeChanged: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    var timePickerIsVisible by remember { mutableStateOf(false) }


    MealData(
        ration = rationFormatted,
        food = food,
        onRationChanged = { onRationChanged(it) },
        navToFoodList = { navToFoodList() },
        addButtonClicked = { addButtonClicked() },
        modifier = modifier.blur(if (timePickerIsVisible) 10.dp else 0.dp)
    )
    Text(
        text = "Hora",
        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
        color = SecondaryDarkest,
        modifier = Modifier.padding(top = 560.dp, start = 60.dp)
    )
    MealTimePicker(
        timePickerIsVisible = timePickerIsVisible,
        mealTime = mealTime,
        onMealTimeChanged = { onMealTimeChanged(it) },
        onTimepickerVisibilityChange = { timePickerIsVisible = it }
    )
    Spacer(modifier = Modifier.height(8.dp))
}





