package com.example.feedm.petsFeature.ui.view.screens.addFoodScreen.components.contentComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.feedm.core.ui.theme.Neutral
import com.example.feedm.core.ui.theme.dimens

@Composable
fun FoodFields(
    foodIsValid: Boolean,
    foodName: String,
    foodCalories: String,
    onFoodNameChanged: (String) -> Unit,
    onFoodCaloriesChanged: (String) -> Unit,
    onCommitButtonClicked: () -> Unit = {},
    onCloseIconClicked: () -> Unit = {}
) {
    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = Neutral
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = { onCommitButtonClicked() },
                        elevation = ButtonDefaults.buttonElevation(),
                        shape = RoundedCornerShape(MaterialTheme.dimens.extraSmall3),
                        modifier = Modifier
                            .padding(bottom = MaterialTheme.dimens.small2)
                            .fillMaxWidth(0.5f)
                            .height(MaterialTheme.dimens.medium3),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFD59E))
                    ) {
                        Text("Confirmar", color = Color.Black)
                    }
                }
            }
        },
    )
    {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(Neutral),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FoodForm(
                modifier = Modifier.weight(0.97f),
                foodIsValid = foodIsValid,
                foodName = foodName,
                foodCalories = foodCalories,
                onFoodNameChanged = { newFoodName -> onFoodNameChanged(newFoodName) },
                onFoodCaloriesChanged = { newCalories -> onFoodCaloriesChanged(newCalories) },
                onCloseIconClicked = { onCloseIconClicked() }
            )
            Spacer(modifier = Modifier.weight(0.03f))
        }
    }
}