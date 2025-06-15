package com.maverickapps.nutripet.petsFeature.ui.view.screens.registerPetScreen

import androidx.compose.runtime.Composable
import com.maverickapps.nutripet.core.ui.theme.NutriPetTheme
import com.maverickapps.nutripet.petsFeature.ui.view.screens.registerPetScreen.components.RegisterPetContent
import com.maverickapps.nutripet.petsFeature.ui.viewmodel.RegisterPetViewmodel


@Composable
fun RegisterPetScreen(
    petId: Int? = null,
    registerPetViewmodel: RegisterPetViewmodel,
    navigateBack: (Boolean) -> Unit = {}
) {
    NutriPetTheme {
        RegisterPetContent(
            petId = petId,
            registerPetViewmodel = registerPetViewmodel,
            navigateBack = navigateBack
        )
    }
}




















