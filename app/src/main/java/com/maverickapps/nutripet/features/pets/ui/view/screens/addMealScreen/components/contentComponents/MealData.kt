package com.maverickapps.nutripet.features.pets.ui.view.screens.addMealScreen.components.contentComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.maverickapps.nutripet.core.ui.theme.Neutral
import com.maverickapps.nutripet.core.ui.theme.Primary
import com.maverickapps.nutripet.core.ui.theme.SecondaryDarkest
import com.maverickapps.nutripet.core.ui.theme.dimens
import com.maverickapps.nutripet.features.pets.domain.objectTasks.food.model.FoodModel
import com.maverickapps.nutripet.features.pets.ui.view.screens.addMealScreen.components.contentComponents.MealDataFields.DataField
import com.maverickapps.nutripet.features.pets.ui.view.screens.addMealScreen.components.contentComponents.MealDataFields.FoodField


@Composable
fun MealData(
    modifier: Modifier = Modifier,
    ration: String,
    food: FoodModel,
    foodIsValid: Boolean,
    rationIsValid: Boolean,
    navToFoodList: () -> Unit,
    addButtonClicked: () -> Unit,
    onRationChanged: (String) -> Unit,
    petName: String
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(color = Neutral),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FoodField(
                petName = petName,
                food = food,
                errorAppearing = foodIsValid,
                modifier = Modifier.weight(0.3f, true), navToFoodList = {navToFoodList()})
            DataField(
                modifier = Modifier.weight(0.6f, true),
                errorAppearing = rationIsValid,
                ration = ration ,
                onRationChanged = {onRationChanged(it)})
            Button(
                colors = ButtonDefaults
                    .buttonColors(containerColor = Primary, contentColor = SecondaryDarkest),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = MaterialTheme.dimens.extraSmall3
                ),
                shape = RoundedCornerShape(MaterialTheme.dimens.extraSmall3),
                onClick = { addButtonClicked() },
                modifier = Modifier
                    .fillMaxWidth(0.3f)
                    .padding(vertical = MaterialTheme.dimens.extraSmall3)
                    .weight(0.1f)
            ) { Text(text = "Añadir") }
        }
    }
}