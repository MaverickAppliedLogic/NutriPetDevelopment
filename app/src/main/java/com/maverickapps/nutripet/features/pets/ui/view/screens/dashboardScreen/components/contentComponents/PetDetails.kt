package com.maverickapps.nutripet.features.pets.ui.view.screens.dashboardScreen.components.contentComponents

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.TileMode
import com.maverickapps.nutripet.core.ui.theme.Neutral
import com.maverickapps.nutripet.core.ui.theme.NeutralLight
import com.maverickapps.nutripet.core.ui.theme.dimens
import com.maverickapps.nutripet.features.pets.domain.objectTasks.food.model.FoodModel
import com.maverickapps.nutripet.features.pets.domain.objectTasks.meal.model.MealModel
import com.maverickapps.nutripet.features.pets.domain.objectTasks.pet.model.PetModel
import com.maverickapps.nutripet.features.pets.ui.view.screens.dashboardScreen.components.contentComponents.petDetailsFields.DataModule
import com.maverickapps.nutripet.features.pets.ui.view.screens.dashboardScreen.components.contentComponents.petDetailsFields.HealthModule
import com.maverickapps.nutripet.features.pets.ui.view.screens.dashboardScreen.components.contentComponents.petDetailsFields.MealsModule
import com.maverickapps.nutripet.features.pets.ui.view.screens.dashboardScreen.components.contentComponents.petDetailsFields.PetList
import java.util.Locale

@Composable
fun PetDetails(
    pets: List<PetModel>,
    petIdSelected: Int?,
    requiredCalories: Int,
    mealsWithFoods: List<Pair<MealModel, FoodModel?>>,
    onPetSelected: (Int) -> Unit,
    onEditIconClicked: () -> Unit,
    onDeleteIconClicked: () -> Unit,
    onMealDataClicked: (Int) -> Unit,
    onMealAddClicked: (Int) -> Unit,
    onMealCloseClicked: (Int) -> Unit,
    onMealDeleteClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.background(Neutral),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (pets.isEmpty() || petIdSelected == null) {
            Text(
                text = "Agrega a tus compañeros",
                fontSize = MaterialTheme.typography.headlineSmall.fontSize
            )
        } else {
            var petMealsChanged by remember{ mutableStateOf(false) }
            val petSelected = pets.find { it.petId == petIdSelected }?: pets.first()
            LaunchedEffect(mealsWithFoods.map { it.first })  {
                petMealsChanged = true
            }
            PetList(
                petList = pets,
                petModel = petSelected,
                petMealsChanged = petMealsChanged,
                onPetSelected = { onPetSelected(it.petId); petMealsChanged = false },
                onEditIconClicked = { onEditIconClicked() },
                onDeleteIconClicked = { onDeleteIconClicked() },
                modifier = Modifier
                    .background(NeutralLight)
                    .fillMaxWidth()
                    .height(MaterialTheme.dimens.extraExtraLarge2)
            )
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(MaterialTheme.dimens.large3)
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
                    .padding(horizontal = MaterialTheme.dimens.small1)
            ) {
                val petWeightFormatted = String
                    .format(Locale.getDefault(),"%.1f", petSelected.petWeight)
                val petSterilizedFormatted = if (petSelected.sterilized) "Si" else "No"
                MealsModule(mealsWithFoods = mealsWithFoods,
                    requiredCalories = requiredCalories,
                    onDataClicked = { onMealDataClicked(it) },
                    onAddIconClicked = { onMealAddClicked(it) },
                    onCloseIconClicked = { onMealCloseClicked(it) },
                    onDeleteIconClicked = { onMealDeleteClicked(it) })
                Spacer(Modifier.height(MaterialTheme.dimens.medium3))
                HealthModule(
                    petWeight = petWeightFormatted,
                    petGoal = petSelected.goal,
                    petActivity = petSelected.activity ?: "-"
                )
                Spacer(Modifier.height(MaterialTheme.dimens.medium3))
                DataModule(
                    petSterilize = petSterilizedFormatted,
                    petGenre = petSelected.genre ?: "-",
                    petAge = petSelected.age.toInt().toString()
                )
                Spacer(Modifier.height(MaterialTheme.dimens.extraExtraLarge2))
            }
        }

    }
}