package com.example.feedm.petsFeature.ui.view.screens.dashboardScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.TileMode
import com.example.feedm.core.ui.theme.Neutral
import com.example.feedm.core.ui.theme.NeutralLight
import com.example.feedm.petsFeature.domain.objectTasks.pet.model.PetModel
import com.example.feedm.petsFeature.ui.view.screens.dashboardScreen.components.CustomBottomBar
import com.example.feedm.petsFeature.ui.view.screens.dashboardScreen.components.DashboardContent
import com.example.feedm.petsFeature.ui.viewmodel.DashboardViewModel

@Composable
fun DashBoardScreen(
    dashboardViewModel: DashboardViewModel,
    navTo: (String, Int?) -> Unit
) {
    val scrollState = rememberScrollState()
    Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
        Box(modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(NeutralLight, Neutral),
                        startY = 0f,
                        endY = Float.POSITIVE_INFINITY,
                        tileMode = TileMode.Clamp)))
        DashboardContent(
            pets = listOf(
                PetModel(
                    petId = 1,
                    "dog",
                    "Masfdfdddf",
                    5.0f,
                    5.0f,
                    null,
                    false,
                    null,
                    "",
                    null
                ),
                PetModel(
                    petId = 1,
                    "cat",
                    "toby",
                    5.0f,
                    5.0f,
                    null,
                    false,
                    null,
                    "",
                    null
                ),
                PetModel(
                    petId = 1,
                    "dog",
                    "toby",
                    5.0f,
                    5.0f,
                    null,
                    false,
                    null,
                    "",
                    null
                ),
                PetModel(
                    petId = 1,
                    "dog",
                    "toby",
                    5.0f,
                    5.0f,
                    null,
                    false,
                    null,
                    "",
                    null
                ),
                PetModel(
                    petId = 1,
                    "dog",
                    "toby",
                    5.0f,
                    5.0f,
                    null,
                    false,
                    null,
                    "",
                    null
                )
            ),
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(scrollState)
        )
        CustomBottomBar(navTo = { navTo(it, null) })
    }
}










