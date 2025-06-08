package com.example.feedm.petsFeature.ui.view.screens.registerPetScreen

import androidx.compose.runtime.Composable
import com.example.feedm.core.ui.theme.TailyCareTheme
import com.example.feedm.petsFeature.ui.view.screens.registerPetScreen.components.RegisterPetContent
import com.example.feedm.petsFeature.ui.viewmodel.RegisterPetViewmodel


@Composable
fun RegisterPetScreen(
    petId: Int? = null,
    registerPetViewmodel: RegisterPetViewmodel,
    navigateBack: (Boolean) -> Unit = {}
) {
    TailyCareTheme {
        RegisterPetContent(
            petId = petId,
            registerPetViewmodel = registerPetViewmodel,
            navigateBack = navigateBack
        )
    }
}




















