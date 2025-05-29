package com.example.feedm.petsFeature.ui.view.screens.dashboardScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.TileMode
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.feedm.core.ui.theme.Neutral
import com.example.feedm.core.ui.theme.NeutralLight
import com.example.feedm.petsFeature.ui.view.screens.dashboardScreen.components.CustomBottomBar
import com.example.feedm.petsFeature.ui.view.screens.dashboardScreen.components.DashboardContent
import com.example.feedm.petsFeature.ui.viewmodel.DashboardViewModel

@Composable
fun DashBoardScreen(
    dashboardViewModel: DashboardViewModel,
    navTo: (String, Int?) -> Unit
) {
    val pets by dashboardViewModel.pets.collectAsStateWithLifecycle()
    val mealsWithFoods by dashboardViewModel.mealsWithFoods.collectAsStateWithLifecycle()
    val petIdSelected by dashboardViewModel.selectedPetId.collectAsStateWithLifecycle()
    LaunchedEffect(pets) {
        if (pets.isNotEmpty()){
        dashboardViewModel.setPetId(pets.first().petId)
            dashboardViewModel.getMeals()
        }
    }
    LaunchedEffect(petIdSelected) {
        if (petIdSelected != null) {
            dashboardViewModel.getMeals()
        }
    }
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
            pets = pets,
            mealsWithFoods = mealsWithFoods,
            onPetSelected = { dashboardViewModel.setPetId(it) },
            onMealDeleteClicked = { dashboardViewModel.deleteMeal(it) },
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(scrollState)
        )
        CustomBottomBar(navTo = {
            println("NavToDashBoard")
            println(petIdSelected)
            navTo(it, petIdSelected) })
    }
}










