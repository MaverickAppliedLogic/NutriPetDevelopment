package com.maverickapps.nutripet.features.pets.ui.view.screens.registerPetScreen.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.maverickapps.nutripet.core.ui.theme.ScreenHeight
import com.maverickapps.nutripet.core.ui.theme.ScreenOrientation
import com.maverickapps.nutripet.features.pets.domain.objectTasks.pet.model.PetModel
import com.maverickapps.nutripet.features.pets.ui.view.screens.registerPetScreen.components.contentComponents.scaffoldComponents.LandscapePetFields
import com.maverickapps.nutripet.features.pets.ui.view.screens.registerPetScreen.components.contentComponents.scaffoldComponents.PortraitPetFields
import com.maverickapps.nutripet.features.pets.ui.view.screens.registerPetScreen.components.contentComponents.scaffolds.LandscapeScaffold
import com.maverickapps.nutripet.features.pets.ui.view.screens.registerPetScreen.components.contentComponents.scaffolds.PortraitScaffold
import com.maverickapps.nutripet.features.pets.ui.view.screens.registerPetScreen.utils.FormStateManager
import com.maverickapps.nutripet.features.pets.ui.viewmodel.RegisterPetViewmodel

@Composable
fun RegisterPetContent(
    petId: Int? = null,
    registerPetViewmodel: RegisterPetViewmodel,
    petToBeAdded: PetModel,
    formStateManager: FormStateManager,
    listOfStates: List<Pair<Int,Boolean>>,
    navigateBack: (Boolean) -> Unit = {}
) {
    var isEditing by remember { mutableStateOf(false) }

    LaunchedEffect(true) {
        if (petId != null) { registerPetViewmodel.getPetById(petId) ; isEditing = true }
    }
    LaunchedEffect(petToBeAdded.petId) {
        if (petToBeAdded.petId != 0){
            formStateManager.triggerAllFields()
        }
    }

    if (ScreenOrientation == Configuration.ORIENTATION_LANDSCAPE || ScreenHeight < 600) {
        LandscapeScaffold { paddingValues ->
            val scrollState = rememberScrollState()
            LandscapePetFields(
                pet = petToBeAdded,
                formStateManager = formStateManager,
                listOfStates = listOfStates,
                onPetChanged = { registerPetViewmodel.petChanged(it) },
                isEditing = isEditing,
                stopChangingForEditing = { isEditing = false },
                buttonClicked = {
                    formStateManager.actionTriggered(
                        0,
                        0,
                        petToBeAdded,
                        navigateBack
                    )
                },
                modifier = Modifier
                    .padding(paddingValues)
                    .verticalScroll(scrollState)
            )
        }
    } else {
        PortraitScaffold(
            formStateManager = formStateManager,
            petToBeAdded = petToBeAdded,
            navigateBack = navigateBack
        ) { paddingValues ->
            PortraitPetFields(
                pet = petToBeAdded,
                formStateManager = formStateManager,
                listOfStates = listOfStates,
                isEditing = isEditing,
                stopChangingForEditing = { isEditing = false },
                onPetChanged = { registerPetViewmodel.petChanged(it) },
                modifier = Modifier.padding(paddingValues)
            )
        }
    }

}




