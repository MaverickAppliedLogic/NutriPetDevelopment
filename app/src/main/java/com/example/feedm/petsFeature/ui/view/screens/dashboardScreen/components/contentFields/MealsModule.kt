package com.example.feedm.petsFeature.ui.view.screens.dashboardScreen.components.contentFields

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.feedm.core.ui.theme.Secondary
import com.example.feedm.core.ui.theme.SecondaryDarkest
import com.example.feedm.petsFeature.domain.objectTasks.food.model.FoodModel
import com.example.feedm.petsFeature.domain.objectTasks.meal.model.MealModel
import com.example.feedm.petsFeature.ui.view.components.ModuleCard
import com.example.feedm.petsFeature.utils.timeFormatter.TimeFormatter
import java.util.Locale

@Composable
fun MealsModule(
    mealsWithFoods: List<Pair<MealModel, FoodModel?>>,
    onDeleteIconClicked: (Int) -> Unit = {},
) {
    val timeFormatter = TimeFormatter()
    var editable by remember { mutableStateOf(false) }
    val caloriesConsumed = remember { mutableStateListOf<Int>() }

    ModuleCard(
        headerTitle = "Comidas Diarias",
        headerIcon = {
            if (mealsWithFoods.isNotEmpty()) {
                Icon(
                    imageVector = if (!editable) Icons.Default.Edit else Icons.Default.Close,
                    contentDescription = "",
                    tint = SecondaryDarkest,
                    modifier = Modifier
                        .padding(bottom = 5.dp)
                        .clickable { editable = !editable }
                )
            }
        },
        captionEnabled = true,
        captionHead = "Recomendado",
        captionTrailing = "${caloriesConsumed.sumOf { it }} kcal"
    ) {
        if (mealsWithFoods.isEmpty()) {
            Text(
                text = "No hay comidas registradas",
                fontStyle = FontStyle.Italic,
                color = Secondary,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
            )
        } else {
            mealsWithFoods.forEach {
                val meal = it.first;val food = it.second
                val formattedTime = timeFormatter.formatMillsToInt(meal.mealTime)
                val formatedStringHour = String.format(
                    Locale.getDefault(),
                    "%02d:%02d", formattedTime.first, formattedTime.second
                )
                ModuleItemMeal(
                    mealId = meal.mealId,
                    mealRation = meal.ration.toInt().toString(),
                    mealCalories = meal.mealCalories,
                    mealHour = formatedStringHour,
                    foodName = food?.foodName?: "No registrado",
                    onAddIconClicked = { calories -> caloriesConsumed.add(calories.toInt()) },
                    onDeleteIconClicked = { id,calories ->
                        caloriesConsumed.remove(calories.toInt());onDeleteIconClicked(id) },
                    modifier = Modifier.height(40.dp), editable = editable
                )
                if (it != mealsWithFoods.last()) {
                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 5.dp, horizontal = 5.dp),
                        color = Secondary
                    )
                }
            }
        }

    }
}