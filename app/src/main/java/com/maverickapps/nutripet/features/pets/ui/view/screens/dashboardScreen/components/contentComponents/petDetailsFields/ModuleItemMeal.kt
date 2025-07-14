package com.maverickapps.nutripet.features.pets.ui.view.screens.dashboardScreen.components.contentComponents.petDetailsFields

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.maverickapps.nutripet.core.ui.theme.SecondaryDark
import com.maverickapps.nutripet.core.ui.theme.SecondaryDarkest
import com.maverickapps.nutripet.core.ui.theme.dimens
import com.maverickapps.nutripet.features.pets.ui.view.screens.dashboardScreen.components.contentComponents.petDetailsFields.moduleItemMealFields.FoodValues
import com.maverickapps.nutripet.features.pets.ui.view.screens.dashboardScreen.components.contentComponents.petDetailsFields.moduleItemMealFields.StateIndicator
import com.maverickapps.nutripet.features.pets.ui.view.screens.dashboardScreen.components.contentComponents.petDetailsFields.moduleItemMealFields.TimeValues

@Composable
fun ModuleItemMeal(
    modifier: Modifier = Modifier,
    mealId: Int = 0,
    state: Int = 1,
    mealCalories: Double = 0.0,
    mealHour: String = "00:00",
    mealRation: String = "0",
    foodName: String = "Food",
    editable: Boolean,
    onDataClicked: (Int) -> Unit = {},
    iconClicked: (Int) -> Unit = {},
    onDeleteIconClicked: (Int) -> Unit
) {
    Row(
        modifier = modifier
            .padding(vertical = MaterialTheme.dimens.extraSmall1,
                horizontal = MaterialTheme.dimens.extraSmall1)
            .background(Color.Transparent),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = foodName, color = SecondaryDarkest,
            modifier = Modifier.weight(0.3f, true)
        )
        TimeValues(
            mealHour = mealHour,
            onTimeClicked = { onDataClicked(mealId) },
            modifier = Modifier.weight(0.25f, true),
            editable = editable
        )
        FoodValues(
            mealRation = mealRation,
            modifier = Modifier.weight(0.20f, true),
            onRationClicked = { onDataClicked(mealId) },
            editable = editable
        )
        if (editable) {
            IconButton(
                onClick = { onDeleteIconClicked(mealId) },
                modifier = Modifier.weight(0.07f, true)
            ) {
                Icon(
                    imageVector = Icons.TwoTone.Delete, contentDescription = "",
                    tint = SecondaryDark, modifier = Modifier.size(MaterialTheme.dimens.small1)
                )
            }
        } else {
            StateIndicator(
                state = state,
                onClick = { iconClicked(mealId) },
                modifier = Modifier.weight(0.15f, true)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ModuleItemMealPreview() {
    ModuleItemMeal(
        modifier = Modifier.fillMaxWidth(),
        onDeleteIconClicked = { _ -> },
        editable = true
    )
}