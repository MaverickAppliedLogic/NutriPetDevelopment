package com.maverickapps.nutripet.petsFeature.ui.view.screens.dashboardScreen

import androidx.compose.runtime.Composable
import com.maverickapps.nutripet.core.ui.theme.NutriPetTheme
import com.maverickapps.nutripet.petsFeature.ui.view.screens.dashboardScreen.components.DashboardContent
import com.maverickapps.nutripet.petsFeature.ui.viewmodel.DashboardViewModel

@Composable
fun DashBoardScreen(
    dashboardViewModel: DashboardViewModel,
    showDialog: Boolean,
    needToRefresh: Boolean,
    fetchCurrentVer: () -> Unit,
    navTo: (String, Int?, Int?) -> Unit
) {
    NutriPetTheme {
        DashboardContent(
            dashboardViewModel = dashboardViewModel,
            showDialog = showDialog,
            dialogSeen ={ fetchCurrentVer() },
            needToRefresh = needToRefresh,
            navTo = navTo)
    }
}










