package com.example.feedm.petsFeature.ui.view.screens.dashboardScreen

import androidx.compose.runtime.Composable
import com.example.feedm.core.ui.theme.TailyCareTheme
import com.example.feedm.petsFeature.ui.view.screens.dashboardScreen.components.DashboardContent
import com.example.feedm.petsFeature.ui.viewmodel.DashboardViewModel

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










