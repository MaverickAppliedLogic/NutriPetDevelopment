package com.maverickapps.nutripet.features.pets.ui.view.screens.dashboardScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.TileMode
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.maverickapps.nutripet.core.ui.components.dialogs.updateDialogs.UpdateDialog
import com.maverickapps.nutripet.core.ui.theme.Neutral
import com.maverickapps.nutripet.core.ui.theme.NeutralDark
import com.maverickapps.nutripet.core.ui.theme.NeutralLight
import com.maverickapps.nutripet.core.ui.theme.SecondaryDarkest
import com.maverickapps.nutripet.core.ui.theme.dimens
import com.maverickapps.nutripet.features.pets.ui.view.components.dialogs.ConfirmPetDialog
import com.maverickapps.nutripet.features.pets.ui.view.screens.dashboardScreen.components.contentComponents.CustomBottomBar
import com.maverickapps.nutripet.features.pets.ui.view.screens.dashboardScreen.components.contentComponents.PetDetails
import com.maverickapps.nutripet.features.pets.ui.viewmodel.DashboardViewModel

@Composable
fun DashboardContent(
    dashboardViewModel: DashboardViewModel,
    needToUpdate: Boolean,
    showUpdateNotes: Boolean,
    needToRefresh: Boolean,
    dialogSeen: () -> Unit,
    navTo: (String, Int?, Int?) -> Unit
) {
    val pets by dashboardViewModel.pets.collectAsState()
    val mealsWithFoods by dashboardViewModel.mealsWithFoods.collectAsStateWithLifecycle()
    val petIdSelected by dashboardViewModel.selectedPetId.collectAsStateWithLifecycle()
    val requiredCalories by dashboardViewModel.requiredCalories.collectAsStateWithLifecycle()
    var openDialog by remember { mutableStateOf(false) }
    val mustRefresh = remember(needToRefresh) { mutableStateOf(needToRefresh) }
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(mustRefresh) {
        if (mustRefresh.value){
            dashboardViewModel.fetchData()
            mustRefresh.value = false
        }
    }
    LaunchedEffect(pets.size) {
        if (pets.isNotEmpty()) {
            dashboardViewModel.fetchData()
        }
    }
    LaunchedEffect(pets.find { it.petId == petIdSelected }) {
        if (petIdSelected != null ) {
            dashboardViewModel.getMeals()
        }
    }
    val scrollState = rememberScrollState()
    val windowInsets = WindowInsets.safeDrawing
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = {
            SnackbarHost(snackBarHostState) {
                Snackbar(snackbarData = it,
                    containerColor = SecondaryDarkest,
                    contentColor = NeutralLight,
                    modifier = Modifier
                        .padding(bottom = MaterialTheme.dimens.medium2)
                        .clickable {
                            snackBarHostState.currentSnackbarData?.dismiss()
                        })
            }
        },
        contentWindowInsets = windowInsets
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(NeutralDark, Neutral),
                        startY = 0f,
                        endY = Float.POSITIVE_INFINITY,
                        tileMode = TileMode.Clamp
                    )
                )
        )
        PetDetails(
            pets = pets,
            petIdSelected = petIdSelected,
            requiredCalories = requiredCalories ?: 0,
            mealsWithFoods = mealsWithFoods,
            onPetSelected = {
                dashboardViewModel.setPetId(it)
            },
            onEditIconClicked = {
                navTo("EditPet", petIdSelected, null)
            },
            onDeleteIconClicked = {
                openDialog = true
            },
            onMealDataClicked = { id ->
                navTo("AddMeal", petIdSelected, id)
            },
            onMealAddClicked = { id ->
                val pair = mealsWithFoods.find { it.first.mealId == id }
                dashboardViewModel.editMeal(pair!!.first.copy(mealState = 0))
            },
            onMealCloseClicked = { id ->
                val pair = mealsWithFoods.find { it.first.mealId == id }
                dashboardViewModel.editMeal(pair!!.first.copy(mealState = 1))
            },
            onMealDeleteClicked = {id ->
                val pair = mealsWithFoods.find { it.first.mealId == id }
                dashboardViewModel.deleteMeal(pair!!.first)
            },
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(scrollState)
        )
        if (openDialog && petIdSelected != null) {
            val petName = pets.find { it.petId == petIdSelected }!!.petName
            ConfirmPetDialog(
                petName = petName,
                petIdSelected = petIdSelected,
                dashboardViewModel = dashboardViewModel,
                snackBarHostState = snackBarHostState,
            ) { openDialog = it }
        }
        UpdateDialog(
            onDismiss = { dialogSeen() },
            needToUpdate = needToUpdate,
            showUpdateNotes = showUpdateNotes,
            modifier = Modifier.fillMaxHeight(0.6f)
        )

        CustomBottomBar(
            bottomPadding = windowInsets.asPaddingValues().calculateBottomPadding(),
            petListSize = pets.size,
            navTo = { navTo(it, petIdSelected, null) }
        )
    }
}