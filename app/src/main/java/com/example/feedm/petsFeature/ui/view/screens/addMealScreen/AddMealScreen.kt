package com.example.feedm.petsFeature.ui.view.screens.addMealScreen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.feedm.core.ui.theme.SecondaryDarkest
import com.example.feedm.petsFeature.ui.view.screens.addMealScreen.components.MealData
import com.example.feedm.petsFeature.ui.view.screens.addMealScreen.components.MealTimePicker
import com.example.feedm.petsFeature.ui.viewmodel.AddMealViewmodel

@Composable
fun AddMealScreen(
    addMealViewmodel: AddMealViewmodel,
    petId: Int,
    navToFoodList: () -> Unit,
    navToBackStack: () -> Unit
) {
    Scaffold {
        AddMealContent(
            petId = petId,
            navToFoodList = { navToFoodList() },
            navToBackStack = { navToBackStack() },
            modifier = Modifier.padding(it)
        )
    }
}

@Composable
fun AddMealContent(
    petId: Int,
    navToFoodList: () -> Unit,
    navToBackStack: () -> Unit,
    modifier: Modifier = Modifier
){
    var timePickerIsVisible by remember { mutableStateOf(false) }

    MealData(
        navToFoodList = { navToFoodList() },
        navToBackStack = { navToBackStack() },
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
        onTimepickerVisibilityChange = { timePickerIsVisible = it }
    )
    Spacer(modifier = Modifier.height(8.dp))
}





