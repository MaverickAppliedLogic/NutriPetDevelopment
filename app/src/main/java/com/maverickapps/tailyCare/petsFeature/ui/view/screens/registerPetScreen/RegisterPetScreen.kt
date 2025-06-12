package com.maverickapps.tailyCare.petsFeature.ui.view.screens.registerPetScreen

import androidx.compose.runtime.Composable
import com.maverickapps.tailyCare.core.ui.theme.TailyCareTheme
import com.maverickapps.tailyCare.petsFeature.ui.view.screens.registerPetScreen.components.RegisterPetContent
import com.maverickapps.tailyCare.petsFeature.ui.viewmodel.RegisterPetViewmodel


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




















