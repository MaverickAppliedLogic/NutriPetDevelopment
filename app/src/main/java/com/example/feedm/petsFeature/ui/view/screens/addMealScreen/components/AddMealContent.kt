package com.example.feedm.petsFeature.ui.view.screens.addMealScreen.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.feedm.core.ui.theme.NeutralDark
import com.example.feedm.core.ui.theme.PrimaryLightest
import com.example.feedm.core.ui.theme.SecondaryDarkest
import com.example.feedm.petsFeature.domain.objectTasks.food.model.FoodModel

@Composable
fun AddMealContent(
    mealIsValid: Pair<String?, Boolean>,
    rationFormatted: String,
    mealTime: Long,
    food: FoodModel,
    navToFoodList: () -> Unit,
    addButtonClicked: (Boolean) -> Unit,
    onRationChanged: (String) -> Unit,
    onMealTimeChanged: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    var timePickerIsVisible by remember { mutableStateOf(false) }
    var openDialog by remember { mutableStateOf(false) }
    var foodIsValid by remember { mutableStateOf(true) }
    var rationIsValid by remember { mutableStateOf(true) }

    MealData(
        ration = rationFormatted,
        food = food,
        foodIsValid = !foodIsValid,
        rationIsValid = !rationIsValid,
        onRationChanged = { onRationChanged(it); foodIsValid = true; rationIsValid = true },
        navToFoodList = { navToFoodList() },
        addButtonClicked = {
            foodIsValid = mealIsValid.first != "Food"
            rationIsValid = mealIsValid.first != "Ration"
            if (mealIsValid.second) openDialog = true},
        modifier = modifier.blur(if (timePickerIsVisible) 10.dp else 0.dp)
    )
    Text(
        text = "Hora",
        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
        color = SecondaryDarkest,
        modifier = Modifier.padding(top = 530.dp, start = 60.dp)
    )
    MealTimePicker(
        timePickerIsVisible = timePickerIsVisible,
        mealTime = mealTime,
        onMealTimeChanged = { onMealTimeChanged(it) },
        onTimepickerVisibilityChange = { timePickerIsVisible = it }
    )
    Spacer(modifier = Modifier.height(8.dp))
    if(openDialog){
        AlertDialog(
            onDismissRequest = { openDialog = false },
            title = {
                Text(text = "¿Quiere registrarla de manera diaria?", color = SecondaryDarkest,
                    fontWeight = FontWeight.Bold)
            },
            text = {
                val calculatedCalories = (food.calories / 100) * rationFormatted.toFloat()
               Text(buildAnnotatedString {

                   withStyle(style = SpanStyle(color = SecondaryDarkest,
                       fontSize = MaterialTheme.typography.titleLarge.fontSize)){
                       append(text = "${food.foodName}, ")
                   }
                   withStyle(style = SpanStyle(color = SecondaryDarkest,
                       fontSize = MaterialTheme.typography.titleLarge.fontSize)){
                       append(text = "$rationFormatted gr")
                   }
                   withStyle(style = SpanStyle(color = SecondaryDarkest, fontWeight = FontWeight.Bold,
                       fontSize = MaterialTheme.typography.titleLarge.fontSize)){
                       append(
                           text = " (${calculatedCalories.toInt()} kcal)"
                       )
                   }
               })
            },
            confirmButton = {
                TextButton(onClick = { addButtonClicked(true);openDialog = false
                }){ Text(text = "Hacerlo diario", color = NeutralDark, fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize) }
            },
            dismissButton = {
                TextButton(onClick = { addButtonClicked(false);openDialog = false })
                { Text(text = "Puntual", color = NeutralDark, fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize) }},
            containerColor = PrimaryLightest,
        )
    }

}