package com.example.feedm.petsFeature.ui.view.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DashBoardScreen(navTo: (String) -> Unit) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
    ) {
        Text(
            text = "Dashboard Screen",
            modifier = Modifier.padding(25.dp)
        )
        Spacer(modifier = Modifier.weight(1f, true))
        Button(
            onClick = { navTo("AddMealScreen") },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Add Meal")
        }
        Spacer(modifier = Modifier.weight(1f, true))
        Button(
            onClick = { navTo("AddFoodScreen") },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Add food")
        }
        Spacer(modifier = Modifier.weight(1f, true))
        Button(
            onClick = { navTo("AddPetScreen") },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Add Pet")
        }
    }
}