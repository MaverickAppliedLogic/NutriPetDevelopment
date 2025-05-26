package com.example.feedm.petsFeature.ui.view.screens.dashboardScreen.components.contentFields

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.feedm.core.ui.theme.Secondary
import com.example.feedm.core.ui.theme.SecondaryDarkest
import com.example.feedm.petsFeature.ui.view.components.ModuleCard
import com.example.feedm.petsFeature.ui.view.components.ModuleItemMeal

@Composable
fun MealsModule() {
    var editable by remember { mutableStateOf(false) }
    ModuleCard(
        headerTitle = "Comidas Diarias",
        headerIcon = {
            Icon(
                imageVector = if (!editable) Icons.Default.Edit else Icons.Default.Close,
                contentDescription = "",
                tint = SecondaryDarkest,
                modifier = Modifier
                    .padding(bottom = 5.dp)
                    .clickable { editable = !editable }
            )
        },
        captionEnabled = true,
        captionHead = "Recomendado",
        captionTrailing = "Example"
    ) {
        ModuleItemMeal(state = 0, modifier = Modifier.height(40.dp), editable = editable)
        HorizontalDivider(
            modifier = Modifier.padding(vertical = 5.dp, horizontal = 5.dp), color = Secondary
        )
        ModuleItemMeal(state = 1, modifier = Modifier.height(40.dp), editable = editable)
        HorizontalDivider(
            modifier = Modifier.padding(vertical = 5.dp, horizontal = 5.dp), color = Secondary
        )
        ModuleItemMeal(state = 2, modifier = Modifier.height(40.dp), editable = editable)
    }
}