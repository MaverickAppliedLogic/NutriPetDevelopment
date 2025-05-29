package com.example.feedm.petsFeature.ui.view.screens.dashboardScreen.components.contentFields

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.feedm.core.ui.theme.Secondary
import com.example.feedm.petsFeature.ui.view.components.ModuleCard
import com.example.feedm.petsFeature.ui.view.components.ModuleItem

@Composable
fun HealthModule(
    petWeight: String,
    petGoal: String,
    petActivity: String
) {
    ModuleCard(headerTitle = "Salud") {
        ModuleItem(headerText = "Peso",
            trailingText = petWeight,
            modifier = Modifier.height(50.dp).padding(end = 5.dp)
        )
        HorizontalDivider(
            modifier = Modifier.padding(vertical = 5.dp, horizontal = 5.dp), color = Secondary
        )
        ModuleItem(headerText = "Objetivo",
            trailingText = petGoal,
            modifier = Modifier.height(50.dp).padding(end = 5.dp)
        )
        HorizontalDivider(
            modifier = Modifier.padding(vertical = 5.dp, horizontal = 5.dp), color = Secondary
        )
        ModuleItem(headerText = "Actividad",
            trailingText = petActivity,
            modifier = Modifier.height(50.dp).padding(end = 5.dp)
        )
    }

}