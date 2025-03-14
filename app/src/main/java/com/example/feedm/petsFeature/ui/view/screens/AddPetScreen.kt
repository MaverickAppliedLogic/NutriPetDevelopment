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
fun AddPetScreen(navToBackStack: () -> Unit){

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
    ) {
        Text(
            text = "Add Pet Screen",
            modifier = Modifier.padding(25.dp)
        )
        Spacer(modifier = Modifier.weight(1f, true))
        Button(
            onClick =  navToBackStack,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Add pet")
        }

    }
}