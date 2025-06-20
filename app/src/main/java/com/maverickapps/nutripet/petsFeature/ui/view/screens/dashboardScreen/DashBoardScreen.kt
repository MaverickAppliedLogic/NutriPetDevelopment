package com.maverickapps.nutripet.petsFeature.ui.view.screens.dashboardScreen

import androidx.compose.runtime.Composable
import com.maverickapps.nutripet.core.ui.theme.NutriPetTheme
import com.maverickapps.nutripet.petsFeature.ui.view.screens.dashboardScreen.components.DashboardContent
import com.maverickapps.nutripet.petsFeature.ui.viewmodel.DashboardViewModel

@Composable
fun DashBoardScreen(
    dashboardViewModel: DashboardViewModel,
    needToUpdate: Boolean,
    showUpdateNotes: Boolean,
    needToRefresh: Boolean,
    dialogSeen: () -> Unit,
    navTo: (String, Int?, Int?) -> Unit
) {
    NutriPetTheme {
        DashboardContent(
            dashboardViewModel = dashboardViewModel,
            showUpdateNotes = showUpdateNotes,
            needToUpdate = needToUpdate,
            dialogSeen ={ dialogSeen() },
            needToRefresh = needToRefresh,
            navTo = navTo)
    }
}










