package com.example.feedm.petsFeature.ui.view.screens.registerPetScreen.components

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.feedm.core.ui.theme.NeutralLight
import com.example.feedm.core.ui.theme.dimens
import com.example.feedm.petsFeature.ui.view.screens.registerPetScreen.components.contentComponents.AddPetContent
import com.example.feedm.petsFeature.ui.view.screens.registerPetScreen.utils.FormItemsInteractionsHandler
import com.example.feedm.petsFeature.ui.view.screens.registerPetScreen.utils.FormStateManager
import com.example.feedm.petsFeature.ui.viewmodel.RegisterPetViewmodel
import rememberFieldStates

@Composable
fun RegisterPetContent(
    petId: Int? = null,
    registerPetViewmodel: RegisterPetViewmodel,
    navigateBack: (Boolean) -> Unit = {}
){
    val formItemsHandler = remember { FormItemsInteractionsHandler() }
    val petToBeAdded by registerPetViewmodel.petToBeRegistered.collectAsStateWithLifecycle()
    val formStateManager = FormStateManager()
    LaunchedEffect(true) {
        if (petId != null) {
            registerPetViewmodel.getPetById(petId)
        }
    }
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
                        shape = RoundedCornerShape(MaterialTheme.dimens.extraSmall2),
                        modifier = Modifier
                            .width(MaterialTheme.dimens.extraLarge2)
                            .height(MaterialTheme.dimens.medium3),
                        onClick = { formStateManager.
                        actionTriggered(0,
                            0,
                            registerPetViewmodel,
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
            registerPetViewmodel = registerPetViewmodel,
            formItemsHandler = formItemsHandler,
            onPetChanged = { registerPetViewmodel.petChanged(it) },
            modifier = Modifier.padding(paddingValues)
        )
    }
}
