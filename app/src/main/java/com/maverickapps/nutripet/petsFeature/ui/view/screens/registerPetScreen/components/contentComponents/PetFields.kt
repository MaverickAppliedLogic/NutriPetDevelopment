package com.maverickapps.nutripet.petsFeature.ui.view.screens.registerPetScreen.components.contentComponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.maverickapps.nutripet.core.ui.theme.dimens
import com.maverickapps.nutripet.petsFeature.domain.objectTasks.pet.model.PetModel
import com.maverickapps.nutripet.petsFeature.ui.view.screens.registerPetScreen.components.contentComponents.fieldsComponents.Form
import com.maverickapps.nutripet.petsFeature.ui.view.screens.registerPetScreen.utils.FormItemsInteractionsHandler
import com.maverickapps.nutripet.petsFeature.ui.view.screens.registerPetScreen.utils.FormItemsInteractionsHandler.Companion.ACTIVITY_FIELD
import com.maverickapps.nutripet.petsFeature.ui.view.screens.registerPetScreen.utils.FormItemsInteractionsHandler.Companion.AGE_FIELD
import com.maverickapps.nutripet.petsFeature.ui.view.screens.registerPetScreen.utils.FormItemsInteractionsHandler.Companion.GOAL_FIELD
import com.maverickapps.nutripet.petsFeature.ui.view.screens.registerPetScreen.utils.FormItemsInteractionsHandler.Companion.PET_NAME_FIELD
import com.maverickapps.nutripet.petsFeature.ui.view.screens.registerPetScreen.utils.FormItemsInteractionsHandler.Companion.SEX_FIELD
import com.maverickapps.nutripet.petsFeature.ui.view.screens.registerPetScreen.utils.FormItemsInteractionsHandler.Companion.STERILIZED_FIELD
import com.maverickapps.nutripet.petsFeature.ui.view.screens.registerPetScreen.utils.FormItemsInteractionsHandler.Companion.WEIGHT_FIELD
import com.maverickapps.nutripet.petsFeature.ui.view.screens.registerPetScreen.utils.FormStateManager
import com.maverickapps.nutripet.petsFeature.ui.viewmodel.RegisterPetViewmodel

@Composable
fun PortraitPetFields(
    modifier: Modifier = Modifier,
    pet: PetModel,
    formStateManager: FormStateManager,
    listOfStates: List<Pair<Int,Boolean>>,
    registerPetViewmodel: RegisterPetViewmodel,
    formItemsHandler: FormItemsInteractionsHandler,
    onPetChanged: (PetModel) -> Unit = {},
) {
    Column(modifier = modifier.fillMaxSize()) {
        ProgressIndicator(
            isLandscapeMode = false,
            validateList = listOfStates.map { it.first },
            modifier = Modifier.weight(0.20f, true)
        )
        Form(
            pet = pet,
            petNameFieldData = listOfStates[PET_NAME_FIELD],
            ageFieldData = listOfStates[AGE_FIELD],
            sexFieldData = listOfStates[SEX_FIELD],
            weightFieldData = listOfStates[WEIGHT_FIELD],
            goalFieldData = listOfStates[GOAL_FIELD],
            sterilizedFieldData = listOfStates[STERILIZED_FIELD],
            activityFieldData = listOfStates[ACTIVITY_FIELD],
            onPetChanged = { onPetChanged(it) },
            onTrailingIconClicked = {
                formStateManager.actionTriggered(
                    1, it, registerPetViewmodel, pet, formItemsHandler, {})
            },
            modifier = Modifier.weight(0.80f, true)
        )
    }
}

@Composable
fun LandscapePetFields(
    modifier: Modifier = Modifier,
    pet: PetModel,
    formStateManager: FormStateManager,
    listOfStates: List<Pair<Int,Boolean>>,
    registerPetViewmodel: RegisterPetViewmodel,
    formItemsHandler: FormItemsInteractionsHandler,
    buttonClicked: () -> Unit,
    onPetChanged: (PetModel) -> Unit = {},
) {
    Column(modifier = modifier.fillMaxSize()) {
        ProgressIndicator(
            isLandscapeMode = true,
            buttonClicked = buttonClicked,
            validateList = listOfStates.map { it.first },
            modifier = Modifier.height(MaterialTheme.dimens.extraLarge3)
        )
        Form(
            pet = pet,
            petNameFieldData = listOfStates[PET_NAME_FIELD],
            ageFieldData = listOfStates[AGE_FIELD],
            sexFieldData = listOfStates[SEX_FIELD],
            weightFieldData = listOfStates[WEIGHT_FIELD],
            goalFieldData = listOfStates[GOAL_FIELD],
            sterilizedFieldData = listOfStates[STERILIZED_FIELD],
            activityFieldData = listOfStates[ACTIVITY_FIELD],
            onPetChanged = { onPetChanged(it) },
            onTrailingIconClicked = {
                formStateManager.actionTriggered(
                    1, it, registerPetViewmodel, pet, formItemsHandler, {})
            },
            modifier = Modifier.height(570.dp)
        )
    }
}