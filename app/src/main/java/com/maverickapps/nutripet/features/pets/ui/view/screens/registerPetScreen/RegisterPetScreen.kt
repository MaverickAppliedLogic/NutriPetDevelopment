package com.maverickapps.nutripet.features.pets.ui.view.screens.registerPetScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.maverickapps.nutripet.core.ui.theme.NutriPetTheme
import com.maverickapps.nutripet.features.pets.ui.view.screens.registerPetScreen.components.RegisterPetContent
import com.maverickapps.nutripet.features.pets.ui.view.screens.registerPetScreen.utils.FormItemsInteractionsHandler
import com.maverickapps.nutripet.features.pets.ui.view.screens.registerPetScreen.utils.FormStateManager
import com.maverickapps.nutripet.features.pets.ui.viewmodel.RegisterPetViewmodel
import rememberFieldStates


@Composable
fun RegisterPetScreen(
    petId: Int? = null,
    registerPetViewmodel: RegisterPetViewmodel,
    navigateBack: (Boolean) -> Unit = {}
) {
    val formItemsHandler = remember { FormItemsInteractionsHandler() }
    val petToBeAdded by registerPetViewmodel.petToBeRegistered.collectAsStateWithLifecycle()
    val formStateManager = FormStateManager(registerPetViewmodel, formItemsHandler)
    val listOfStates = rememberFieldStates(formItemsHandler)

    NutriPetTheme {
        RegisterPetContent(
            petId = petId,
            petToBeAdded = petToBeAdded,
            formStateManager = formStateManager,
            listOfStates = listOfStates,
            registerPetViewmodel = registerPetViewmodel,
            navigateBack = navigateBack
        )
    }
}




















