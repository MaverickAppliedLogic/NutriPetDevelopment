package com.example.feedm.petsFeature.ui.view.screens.dashboardScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.unit.dp
import com.example.feedm.core.ui.theme.NeutralLight
import com.example.feedm.core.ui.theme.Primary
import com.example.feedm.core.ui.theme.SecondaryDarkest
import com.example.feedm.petsFeature.ui.viewmodel.DashboardViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.feedm.petsFeature.ui.view.screens.dashboardScreen.components.DashboardContent

@Composable
fun DashBoardScreen(
    viewmodel: DashboardViewModel = viewModel(),
    navTo: (String, Int?) -> Unit
) {
    val pets by viewmodel.pets.collectAsStateWithLifecycle()
    val meals by viewmodel.meals.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) { viewmodel.init() }
    val scrollState = rememberScrollState()
    Scaffold(modifier = Modifier.fillMaxSize(),
        bottomBar = {}) { paddingValues ->
        androidx.compose.foundation.layout.Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(NeutralLight, NeutralLight),
                        startY = 0f,
                        endY = Float.POSITIVE_INFINITY,
                        tileMode = TileMode.Clamp
                    )
                )
        )
        DashboardContent(
            pets = pets,
            meals = meals,
            onEditMeal = { viewmodel.editMeal(it) },
            onDeleteMeal = { viewmodel.deleteMeal(it) },
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(scrollState)
        )
        CustomBottomBar(navto = { navTo(it, null) })
    }
}

@Composable
fun CustomBottomBar(
    navto: (String) -> Unit = {}
) {
    androidx.compose.foundation.layout.Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        androidx.compose.foundation.layout.Row(
            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Primary),
                        startY = 30f,
                        endY = Float.POSITIVE_INFINITY,
                        tileMode = TileMode.Clamp
                    )
                )
        ) {
            FloatingActionButton(
                onClick = {navto("AddMeal")},
                shape = androidx.compose.foundation.shape.CircleShape, containerColor = Primary,
                contentColor = SecondaryDarkest,
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .size(65.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "")
            }
        }
    }
}
