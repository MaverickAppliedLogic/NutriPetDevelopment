package com.example.feedm.petsFeature.ui.view.screens.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.feedm.core.ui.theme.Neutral
import com.example.feedm.core.ui.theme.NeutralLight
import com.example.feedm.petsFeature.domain.objectTasks.pet.model.PetModel
import com.example.feedm.petsFeature.ui.view.screens.dashboard.components.DataModule
import com.example.feedm.petsFeature.ui.view.screens.dashboard.components.HealthModule
import com.example.feedm.petsFeature.ui.view.screens.dashboard.components.MealsModule
import com.example.feedm.petsFeature.ui.view.screens.dashboard.components.PetList
import com.example.feedm.petsFeature.domain.objectTasks.meal.model.MealModel
import androidx.compose.material3.Text

@Composable
fun MainContent(
    pets: List<PetModel>,
    meals: List<MealModel>,
    onEditMeal: (MealModel) -> Unit,
    onDeleteMeal: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.background(Neutral),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (pets.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("No hay mascotas registradas. Agrega una para comenzar.")
            }
            return
        }
        PetList(
            petList = pets,
            modifier = Modifier
                .background(NeutralLight)
                .fillMaxWidth()
                .weight(1f, true)
        )
        Row(
            Modifier
                .height(50.dp)
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(NeutralLight)
            )
        }
        Column(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 15.dp)
        ) {
            MealsModule(
                meals = meals,
                onEditMeal = onEditMeal,
                onDeleteMeal = onDeleteMeal
            )
            Spacer(Modifier.height(50.dp))
            HealthModule()
            Spacer(Modifier.height(50.dp))
            DataModule()
            Spacer(Modifier.height(100.dp))
        }
    }
}
