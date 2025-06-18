package com.maverickapps.nutripet.petsFeature.ui.view.screens.dashboardScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.maverickapps.nutripet.core.ui.theme.Error
import com.maverickapps.nutripet.core.ui.theme.Neutral
import com.maverickapps.nutripet.core.ui.theme.NeutralDark
import com.maverickapps.nutripet.core.ui.theme.NeutralLight
import com.maverickapps.nutripet.core.ui.theme.SecondaryDarkest
import com.maverickapps.nutripet.core.ui.theme.dimens
import com.maverickapps.nutripet.petsFeature.ui.view.screens.dashboardScreen.components.contentComponents.CustomBottomBar
import com.maverickapps.nutripet.petsFeature.ui.view.screens.dashboardScreen.components.contentComponents.PetDetails
import com.maverickapps.nutripet.petsFeature.ui.viewmodel.DashboardViewModel
import kotlinx.coroutines.launch

@Composable
fun DashboardContent(
    dashboardViewModel: DashboardViewModel,
    needToRefresh: Boolean,
    navTo: (String, Int?, Int?) -> Unit
){
    val pets by dashboardViewModel.pets.collectAsState()
    val mealsWithFoods by dashboardViewModel.mealsWithFoods.collectAsStateWithLifecycle()
    val petIdSelected by dashboardViewModel.selectedPetId.collectAsStateWithLifecycle()
    val requiredCalories by dashboardViewModel.requiredCalories.collectAsStateWithLifecycle()
    var openDialog by remember { mutableStateOf(false) }
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    if (needToRefresh) dashboardViewModel.fetchData()
    LaunchedEffect(pets.size) {
        if (pets.isNotEmpty()) {
            dashboardViewModel.setPetId(pets.first().petId)
        }
    }
    LaunchedEffect(petIdSelected) {
        if (petIdSelected != null) {
            dashboardViewModel.getMeals()
        }
    }
    val scrollState = rememberScrollState()
    Scaffold(modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackBarHostState){
            Snackbar(snackbarData = it,
                containerColor = SecondaryDarkest,
                contentColor = NeutralLight,
                modifier = Modifier.padding(bottom = MaterialTheme.dimens.medium2).clickable {
                    snackBarHostState.currentSnackbarData?.dismiss() })
        } },
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
                println("onDeleteIconClicked")
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
            onMealDeleteClicked = {
                dashboardViewModel.deleteMeal(it)
            },
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(scrollState)
        )
        if(openDialog && petIdSelected != null){
            val petName = pets.find { it.petId == petIdSelected }!!.petName

            AlertDialog(
                onDismissRequest = { openDialog = false },
                title = {
                    Text(buildAnnotatedString {
                        withStyle(style = SpanStyle(color = SecondaryDarkest)){
                            append("¿Desea eliminar a")
                        }
                        withStyle(style = SpanStyle(color = Error,
                            fontWeight = FontWeight.Bold,
                        )
                        ) {
                            append(" $petName ")
                        }
                        withStyle(style = SpanStyle(color = SecondaryDarkest)){
                            append("?")
                        }
                    })
                },
                text = {
                    Text(text = "Una vez eliminado no podrá recuperar ninguno de sus datos",
                        color = SecondaryDarkest
                    )
                },
                confirmButton = {
                    TextButton(onClick = {
                        openDialog = false
                    }){ Text(text = "Cancelar", color = SecondaryDarkest, fontWeight = FontWeight.Bold) }
                },
                dismissButton = {
                    TextButton(onClick = {
                        dashboardViewModel.deletePet(petIdSelected!!);openDialog = false
                        scope.launch {
                            snackBarHostState.showSnackbar("$petName eliminado/a correctamente.")
                        }
                    }){ Text(text = "Eliminar", color = Error) } },
                containerColor = NeutralLight,
            )
        }
        CustomBottomBar(
            petListSize = pets.size,
            navTo = { navTo(it, petIdSelected, null) }
        )
    }
}