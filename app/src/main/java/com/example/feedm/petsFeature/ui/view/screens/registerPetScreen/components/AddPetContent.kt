package com.example.feedm.petsFeature.ui.view.screens.registerPetScreen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.feedm.petsFeature.domain.objectTasks.pet.model.PetModel
import com.example.feedm.petsFeature.ui.view.screens.registerPetScreen.components.addPetContentComponents.Form
import com.example.feedm.petsFeature.ui.view.screens.registerPetScreen.components.addPetContentComponents.ProgressIndicator
import com.example.feedm.petsFeature.ui.view.screens.registerPetScreen.utils.FormItemsInteractionsHandler
import com.example.feedm.petsFeature.ui.view.screens.registerPetScreen.utils.FormItemsInteractionsHandler.Companion.ACTIVITY_FIELD
import com.example.feedm.petsFeature.ui.view.screens.registerPetScreen.utils.FormItemsInteractionsHandler.Companion.AGE_FIELD
import com.example.feedm.petsFeature.ui.view.screens.registerPetScreen.utils.FormItemsInteractionsHandler.Companion.GOAL_FIELD
import com.example.feedm.petsFeature.ui.view.screens.registerPetScreen.utils.FormItemsInteractionsHandler.Companion.PET_NAME_FIELD
import com.example.feedm.petsFeature.ui.view.screens.registerPetScreen.utils.FormItemsInteractionsHandler.Companion.SEX_FIELD
import com.example.feedm.petsFeature.ui.view.screens.registerPetScreen.utils.FormItemsInteractionsHandler.Companion.STERILIZED_FIELD
import com.example.feedm.petsFeature.ui.view.screens.registerPetScreen.utils.FormItemsInteractionsHandler.Companion.WEIGHT_FIELD
import com.example.feedm.petsFeature.ui.view.screens.registerPetScreen.utils.FormStateManager
import com.example.feedm.petsFeature.ui.viewmodel.AddPetViewmodel

@Composable
fun AddPetContent(
    modifier: Modifier = Modifier,
    pet: PetModel,
    formStateManager: FormStateManager,
    listOfStates: List<Pair<Int,Boolean>>,
    addPetViewmodel: AddPetViewmodel,
    formItemsHandler: FormItemsInteractionsHandler,
    onPetChanged: (PetModel) -> Unit = {},
) {
    Column(modifier = modifier.fillMaxSize()) {
        ProgressIndicator(
            validateList = listOfStates.map { it.first },
            modifier = Modifier.weight(0.25f, true)
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
                    1, it, addPetViewmodel, pet, formItemsHandler, {})
            },
            modifier = Modifier.weight(0.75f, true)
        )
    }
}