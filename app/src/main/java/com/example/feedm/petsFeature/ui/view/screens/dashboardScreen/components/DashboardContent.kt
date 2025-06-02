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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.unit.dp
import com.example.feedm.core.ui.theme.Neutral
import com.example.feedm.core.ui.theme.NeutralLight
import com.example.feedm.petsFeature.domain.objectTasks.food.model.FoodModel
import com.example.feedm.petsFeature.domain.objectTasks.meal.model.MealModel
import com.example.feedm.petsFeature.domain.objectTasks.pet.model.PetModel
import com.example.feedm.petsFeature.ui.view.screens.dashboardScreen.components.contentFields.DataModule
import com.example.feedm.petsFeature.ui.view.screens.dashboardScreen.components.contentFields.HealthModule
import com.example.feedm.petsFeature.ui.view.screens.dashboardScreen.components.contentFields.MealsModule
import com.example.feedm.petsFeature.ui.view.screens.dashboardScreen.components.contentFields.PetList
import java.util.Locale

@Composable
fun DashboardContent(
    pets: List<PetModel>,
    petIdSelected: Int?,
    requiredCalories: Int,
    mealsWithFoods: List<Pair<MealModel, FoodModel?>>,
    onPetSelected: (Int) -> Unit,
    onEditIconClicked: (Int) -> Unit,
    onDeleteIconClicked: () -> Unit,
    onMealDataClicked: (Int) -> Unit,
    onMealAddClicked: (Int) -> Unit,
    onMealDeleteClicked: (Int) -> Unit,
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
            var petSelected by remember { mutableStateOf(pets.first()) }
            LaunchedEffect(petIdSelected) {
                if(petIdSelected != null) petSelected = pets.first { it.petId == petIdSelected }
            }
            var petIndexSelected by remember { mutableIntStateOf(0) }
            LaunchedEffect(petIndexSelected) {
                onPetSelected(pets[petIndexSelected].petId)
            }

            PetList(
                petList = pets,
                onPetSelected = { petIndexSelected = it },
                onEditIconClicked = { onEditIconClicked(it) },
                onDeleteIconClicked = { onDeleteIconClicked() },
                modifier = Modifier
                    .background(NeutralLight)
                    .fillMaxWidth()
                    .height(250.dp)
            )
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(75.dp)
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
                val petWeightFormatted = String
                    .format(Locale.getDefault(),"%.1f", petSelected.petWeight)
                val petSterilizedFormatted = if (petSelected.sterilized) "Si" else "No"
                MealsModule(mealsWithFoods = mealsWithFoods,
                    requiredCalories = requiredCalories,
                    onDataClicked = { onMealDataClicked(it) },
                    onAddIconClicked = { onMealAddClicked(it) },
                    onDeleteIconClicked = { onMealDeleteClicked(it) })
                Spacer(Modifier.height(50.dp))
                HealthModule(
                    petWeight = petWeightFormatted,
                    petGoal = petSelected.goal,
                    petActivity = petSelected.activity ?: "-"
                )
                Spacer(Modifier.height(50.dp))
                DataModule(
                    petSterilize = petSterilizedFormatted,
                    petGenre = petSelected.genre ?: "-",
                    petAge = petSelected.age.toInt().toString()
                )
                Spacer(Modifier.height(150.dp))
            }
        }

    }
}