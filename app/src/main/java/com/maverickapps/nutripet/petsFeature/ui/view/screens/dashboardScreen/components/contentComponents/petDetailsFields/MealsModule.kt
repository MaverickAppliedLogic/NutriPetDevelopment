package com.maverickapps.nutripet.petsFeature.ui.view.screens.dashboardScreen.components.contentComponents.petDetailsFields

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import com.maverickapps.nutripet.core.ui.theme.Secondary
import com.maverickapps.nutripet.core.ui.theme.SecondaryDarkest
import com.maverickapps.nutripet.core.ui.theme.dimens
import com.maverickapps.nutripet.petsFeature.domain.objectTasks.food.model.FoodModel
import com.maverickapps.nutripet.petsFeature.domain.objectTasks.meal.model.MealModel
import com.maverickapps.nutripet.petsFeature.ui.view.components.ModuleCard
import com.maverickapps.nutripet.petsFeature.utils.timeFormatter.TimeFormatter
import java.util.Locale

@Composable
fun MealsModule(
    mealsWithFoods: List<Pair<MealModel, FoodModel?>>,
    requiredCalories: Int,
    onDataClicked: (Int) -> Unit = {},
    onAddIconClicked: (Int) -> Unit = {},
    onCloseIconClicked: (Int) -> Unit = {},
    onDeleteIconClicked: (Int) -> Unit = {},
) {
    val timeFormatter = TimeFormatter()
    var editable by remember { mutableStateOf(false) }
    val caloriesConsumed = mealsWithFoods
        .filter { it.first.mealState == 0 }
        .map { it.first.mealCalories.toInt() }

    ModuleCard(
        headerTitle = "Comidas Diarias",
        headerIcon = {
            if (mealsWithFoods.isNotEmpty()) {
                Icon(
                    imageVector = if (!editable) Icons.Default.Edit else Icons.Default.Close,
                    contentDescription = "",
                    tint = SecondaryDarkest,
                    modifier = Modifier
                        .padding(bottom = MaterialTheme.dimens.extraSmall1)
                        .clickable { editable = !editable }
                )
            }
        },
        captionEnabled = true,
        captionHead = "Recomendado",
        captionTrailing = "${caloriesConsumed.sumOf { it }} / $requiredCalories kcal"
    ) {
        if (mealsWithFoods.isEmpty()) {
            Text(
                text = "No hay comidas registradas",
                fontStyle = FontStyle.Italic,
                color = Secondary,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = MaterialTheme.dimens.extraSmall3)
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
                    state = meal.mealState,
                    mealCalories = meal.mealCalories,
                    mealHour = formatedStringHour,
                    foodName = food?.foodName?: "No registrado",
                    onDataClicked = { id -> onDataClicked(id) },
                    iconClicked = { id ->
                        when(meal.mealState){
                            0 -> onCloseIconClicked(id)
                            1 -> onAddIconClicked(id)
                            2 -> onAddIconClicked(id)
                        }
                    },
                    onDeleteIconClicked = { id -> onDeleteIconClicked(id) },
                    modifier = Modifier.height(MaterialTheme.dimens.medium2),
                    editable = editable
                )
                if (it != mealsWithFoods.last()) {
                    HorizontalDivider(
                        modifier = Modifier.padding(
                            vertical = MaterialTheme.dimens.extraSmall1,
                            horizontal = MaterialTheme.dimens.extraSmall1
                        ),
                        color = Secondary
                    )
                }
            }
        }

    }
}