package com.example.feedm.petsFeature.ui.view.screens.dashboard.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.feedm.core.ui.theme.Secondary
import com.example.feedm.core.ui.theme.SecondaryDarkest
import com.example.feedm.petsFeature.domain.objectTasks.meal.model.MealModel
import com.example.feedm.petsFeature.ui.view.components.ModuleCard
import com.example.feedm.petsFeature.ui.view.components.ModuleItemMeal

@Composable
fun MealsModule(
    meals: List<MealModel>,
    onEditMeal: (MealModel) -> Unit,
    onDeleteMeal: (Int) -> Unit
) {
    var editable = remember { mutableStateOf(false) }
    ModuleCard(
        headerTitle = "Comidas Diarias",
        headerIcon = {
            Icon(
                imageVector = if (!editable.value) Icons.Default.Edit else Icons.Default.Close,
                contentDescription = "",
                tint = SecondaryDarkest,
                modifier = Modifier
                    .clickable { editable.value = !editable.value }
            )
        },
        captionEnabled = true,
        captionHead = "Recomendado",
        captionTrailing = "Example"
    ) {
        if (meals.isEmpty()) {
            Text("No hay comidas registradas. Agrega una comida para comenzar.", modifier = Modifier)
        } else {
            meals.forEachIndexed { idx, meal ->
                ModuleItemMeal(
                    mealHour = formatMealHour(meal.mealTime),
                    mealRation = meal.ration.toString(),
                    foodName = "Comida #${meal.foodId}",
                    state = 1,
                    editable = editable.value,
                    iconClicked = { onDeleteMeal(meal.mealId) },
                    modifier = Modifier.height(40.dp)
                )
                if (idx < meals.lastIndex) {
                    HorizontalDivider(
                        modifier = Modifier,
                        color = Secondary
                    )
                }
            }
        }
    }
}

fun formatMealHour(mealTime: Long): String {
    val totalMinutes = (mealTime / 60000).toInt()
    val hours = totalMinutes / 60
    val minutes = totalMinutes % 60
    return String.format("%02d:%02d", hours, minutes)
}
