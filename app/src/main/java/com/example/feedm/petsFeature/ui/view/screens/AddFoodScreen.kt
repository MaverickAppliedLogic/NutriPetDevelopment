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
fun AddFoodScreen(navToBackStack: () -> Unit,
                  navToFoodList: () -> Unit){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
    ) {
        Spacer(modifier = Modifier.weight(1f, true))
        Text(
            text = "Add Food Screen",
            modifier = Modifier.padding(25.dp)
        )
        Spacer(modifier = Modifier.weight(1f, true))
        Button(
            onClick =  navToBackStack,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Go back")
        }
        Spacer(modifier = Modifier.weight(1f, true))
        Button(
            onClick =  navToFoodList,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Add Food")
        }
        Spacer(modifier = Modifier.weight(1f, true))
    }

}