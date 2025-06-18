package com.maverickapps.nutripet.petsFeature.ui.view.screens.dashboardScreen

import androidx.compose.runtime.Composable
import com.maverickapps.nutripet.core.ui.theme.NutriPetTheme
import com.maverickapps.nutripet.petsFeature.ui.view.screens.dashboardScreen.components.DashboardContent
import com.maverickapps.nutripet.petsFeature.ui.viewmodel.DashboardViewModel

@Composable
fun DashBoardScreen(
    dashboardViewModel: DashboardViewModel,
    needToRefresh: Boolean,
    navTo: (String, Int?, Int?) -> Unit
) {
    NutriPetTheme {
        DashboardContent(
            dashboardViewModel = dashboardViewModel,
            needToRefresh = needToRefresh,
            navTo = navTo)
    }
}










