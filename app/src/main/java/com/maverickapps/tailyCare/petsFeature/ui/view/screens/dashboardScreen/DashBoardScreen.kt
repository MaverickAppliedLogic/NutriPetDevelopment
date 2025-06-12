package com.maverickapps.tailyCare.petsFeature.ui.view.screens.dashboardScreen

import androidx.compose.runtime.Composable
import com.maverickapps.tailyCare.core.ui.theme.TailyCareTheme
import com.maverickapps.tailyCare.petsFeature.ui.view.screens.dashboardScreen.components.DashboardContent
import com.maverickapps.tailyCare.petsFeature.ui.viewmodel.DashboardViewModel

@Composable
fun DashBoardScreen(
    dashboardViewModel: DashboardViewModel,
    needToRefresh: Boolean,
    navTo: (String, Int?, Int?) -> Unit
) {
    TailyCareTheme {
        DashboardContent(
            dashboardViewModel = dashboardViewModel,
            needToRefresh = needToRefresh,
            navTo = navTo)
    }
}










