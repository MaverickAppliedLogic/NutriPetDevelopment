package com.maverickapps.tailyCare.petsFeature.ui.view.screens.addMealScreen.components.contentComponents

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
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
import com.maverickapps.tailyCare.core.ui.theme.NeutralDark
import com.maverickapps.tailyCare.core.ui.theme.PrimaryLightest
import com.maverickapps.tailyCare.core.ui.theme.SecondaryDarkest
import com.maverickapps.tailyCare.core.ui.theme.dimens
import com.maverickapps.tailyCare.petsFeature.domain.objectTasks.food.model.FoodModel

@Composable
fun AddMealFields(
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
        modifier = modifier.blur(
            if (timePickerIsVisible) MaterialTheme.dimens.extraSmall3 else 0.dp
        )
    )
    MealTimePicker(
        timePickerIsVisible = timePickerIsVisible,
        mealTime = mealTime,
        onMealTimeChanged = { onMealTimeChanged(it) },
        onTimepickerVisibilityChange = { timePickerIsVisible = it },
    )
    Spacer(modifier = Modifier.height(MaterialTheme.dimens.extraSmall2))
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