package com.example.feedm.petsFeature.ui.view.screens.registerPetScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.feedm.core.ui.theme.NeutralLight
import com.example.feedm.petsFeature.ui.view.screens.registerPetScreen.components.AddPetContent
import com.example.feedm.petsFeature.ui.view.screens.registerPetScreen.utils.FormItemsInteractionsHandler
import com.example.feedm.petsFeature.ui.view.screens.registerPetScreen.utils.FormStateManager
import com.example.feedm.petsFeature.ui.viewmodel.AddPetViewmodel
import rememberFieldStates


@Preview
@Composable
fun RegisterPetScreen(
    petId: Int? = null,
    addPetViewmodel: AddPetViewmodel = viewModel(),
    navigateBack: () -> Unit = {}
) {
    val formItemsHandler = remember { FormItemsInteractionsHandler() }
    val petToBeAdded by addPetViewmodel.petToBeRegistered.collectAsStateWithLifecycle()
    val formStateManager = FormStateManager()
    LaunchedEffect(true) {
        if (petId != null) {
            addPetViewmodel.getPetById(petId)
        }
    }
// Colectores para FieldState
    val listOfStates = rememberFieldStates(formItemsHandler)
    Scaffold(
        bottomBar = {
            BottomAppBar(containerColor = NeutralLight) {
                Row(
                    Modifier
                        .fillMaxSize()
                        .background(Color.Transparent),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        shape = RoundedCornerShape(5.dp),
                        modifier = Modifier
                            .width(150.dp)
                            .height(50.dp),
                        onClick = { formStateManager.
                           actionTriggered(0,
                               0,
                               addPetViewmodel,
                               petToBeAdded,
                               formItemsHandler,
                               navigateBack)
                        },
                    ) {
                        Text(text = "Confirmar")
                    }
                }
            }
        },
        contentWindowInsets = WindowInsets.safeDrawing
    ) { paddingValues ->
        AddPetContent(
            pet = petToBeAdded,
            formStateManager = formStateManager,
            listOfStates = listOfStates,
            addPetViewmodel = addPetViewmodel,
            formItemsHandler = formItemsHandler,
            onPetChanged = { addPetViewmodel.petChanged(it) },
            modifier = Modifier.padding(paddingValues)
        )
    }
}




















