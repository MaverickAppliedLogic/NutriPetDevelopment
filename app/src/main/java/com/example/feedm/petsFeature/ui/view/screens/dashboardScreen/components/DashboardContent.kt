package com.example.feedm.petsFeature.ui.view.screens.dashboardScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.unit.dp
import com.example.feedm.core.ui.theme.Neutral
import com.example.feedm.core.ui.theme.NeutralLight
import com.example.feedm.petsFeature.domain.objectTasks.pet.model.PetModel
import com.example.feedm.petsFeature.ui.view.screens.dashboardScreen.components.contentFields.DataModule
import com.example.feedm.petsFeature.ui.view.screens.dashboardScreen.components.contentFields.HealthModule
import com.example.feedm.petsFeature.ui.view.screens.dashboardScreen.components.contentFields.MealsModule
import com.example.feedm.petsFeature.ui.view.screens.dashboardScreen.components.contentFields.PetList

@Composable
fun DashboardContent(
    pets: List<PetModel>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.background(Neutral),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (pets.isEmpty()) {
            Text(
                text = "Agrega a tus compañeros",
                fontSize = MaterialTheme.typography.headlineSmall.fontSize
            )
        } else {
            PetList(
                pets,
                modifier =
                Modifier
                    .background(NeutralLight)
                    .fillMaxWidth()
                    .height(250.dp)
            )
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(NeutralLight, Neutral),
                                startY = 0f,
                                endY = Float.POSITIVE_INFINITY,
                                tileMode = TileMode.Clamp
                            )
                        )
                )
            }
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)
            ) {
                MealsModule()
                Spacer(Modifier.height(50.dp))
                HealthModule()
                Spacer(Modifier.height(50.dp))
                DataModule()
                Spacer(Modifier.height(100.dp))
            }
        }

    }
}