package com.example.feedm.petsFeature.ui.view.screens.addMealScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.feedm.core.ui.theme.Neutral
import com.example.feedm.core.ui.theme.Primary
import com.example.feedm.core.ui.theme.SecondaryDarkest
import com.example.feedm.petsFeature.ui.view.screens.addMealScreen.components.MealDataFields.DataField
import com.example.feedm.petsFeature.ui.view.screens.addMealScreen.components.MealDataFields.MealField

@Composable
fun MealData(
    modifier: Modifier = Modifier,
    navToFoodList: () -> Unit,
    navToBackStack: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(color = Neutral),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MealField(modifier = Modifier.weight(0.3f, true), navToFoodList = {navToFoodList()})
            DataField(modifier = Modifier.weight(0.6f, true))
            Button(
                colors = ButtonDefaults
                    .buttonColors(containerColor = Primary, contentColor = SecondaryDarkest),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 10.dp),
                shape = RoundedCornerShape(10.dp),
                onClick = { navToBackStack() },
                modifier = Modifier
                    .fillMaxWidth(0.3f)
                    .padding(vertical = 10.dp)
                    .weight(0.1f)
            ) { Text(text = "Añadir") }
        }
    }
}