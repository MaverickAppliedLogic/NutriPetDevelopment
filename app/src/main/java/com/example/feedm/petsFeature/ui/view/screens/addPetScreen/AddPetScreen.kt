package com.example.feedm.petsFeature.ui.view.screens.addPetScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.derivedStateOf
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
import com.example.feedm.petsFeature.domain.objectTasks.pet.model.PetModel
import com.example.feedm.petsFeature.ui.view.screens.addPetScreen.components.Form
import com.example.feedm.petsFeature.ui.view.screens.addPetScreen.components.ProgressIndicator
import com.example.feedm.petsFeature.ui.view.screens.addPetScreen.components.formFields.FormFieldStates.INVALID
import com.example.feedm.petsFeature.ui.view.screens.addPetScreen.components.formFields.FormFieldStates.VALID
import com.example.feedm.petsFeature.ui.view.screens.addPetScreen.components.formFields.FormFieldStates.WAITING
import com.example.feedm.petsFeature.ui.view.screens.addPetScreen.utils.FormItemsInteractionsHandler
import com.example.feedm.petsFeature.ui.view.screens.addPetScreen.utils.FormItemsInteractionsHandler.Companion.ACTIVITY_FIELD
import com.example.feedm.petsFeature.ui.view.screens.addPetScreen.utils.FormItemsInteractionsHandler.Companion.AGE_FIELD
import com.example.feedm.petsFeature.ui.view.screens.addPetScreen.utils.FormItemsInteractionsHandler.Companion.GOAL_FIELD
import com.example.feedm.petsFeature.ui.view.screens.addPetScreen.utils.FormItemsInteractionsHandler.Companion.PET_NAME_FIELD
import com.example.feedm.petsFeature.ui.view.screens.addPetScreen.utils.FormItemsInteractionsHandler.Companion.SEX_FIELD
import com.example.feedm.petsFeature.ui.view.screens.addPetScreen.utils.FormItemsInteractionsHandler.Companion.STERILIZED_FIELD
import com.example.feedm.petsFeature.ui.view.screens.addPetScreen.utils.FormItemsInteractionsHandler.Companion.WEIGHT_FIELD
import com.example.feedm.petsFeature.ui.viewmodel.AddPetViewmodel


@Preview
@Composable
fun AddPetScreen(
    addPetViewmodel: AddPetViewmodel = viewModel(),
    navigateBack: () -> Unit = {}
) {
    val formItemsHandler = remember { FormItemsInteractionsHandler() }
    val petToBeAdded by addPetViewmodel.petToBeAdded.collectAsStateWithLifecycle()

// Colectores para FieldState
    val petNameFieldState by formItemsHandler.petNameFieldState.collectAsStateWithLifecycle()
    val ageFieldState by formItemsHandler.ageFieldState.collectAsStateWithLifecycle()
    val sexFieldState by formItemsHandler.sexFieldState.collectAsStateWithLifecycle()
    val weightFieldState by formItemsHandler.weightFieldState.collectAsStateWithLifecycle()
    val goalFieldState by formItemsHandler.goalFieldState.collectAsStateWithLifecycle()
    val sterilizedFieldState by formItemsHandler.sterilizedFieldState.collectAsStateWithLifecycle()
    val activityFieldState by formItemsHandler.activityFieldState.collectAsStateWithLifecycle()

// Colectores para FieldVisibility
    val petNameFieldVisibility by formItemsHandler.petNameFieldVisibility.collectAsStateWithLifecycle()
    val ageFieldVisibility by formItemsHandler.ageFieldVisibility.collectAsStateWithLifecycle()
    val sexFieldVisibility by formItemsHandler.sexFieldVisibility.collectAsStateWithLifecycle()
    val weightFieldVisibility by formItemsHandler.weightFieldVisibility.collectAsStateWithLifecycle()
    val goalFieldVisibility by formItemsHandler.goalFieldVisibility.collectAsStateWithLifecycle()
    val sterilizedFieldVisibility by formItemsHandler.sterilizedFieldVisibility.collectAsStateWithLifecycle()
    val activityFieldVisibility by formItemsHandler.activityField.collectAsStateWithLifecycle()


    // Uso de derivedStateOf para evitar recomposición innecesaria
    val petNameFieldData = remember {
        derivedStateOf { Pair(petNameFieldState, petNameFieldVisibility) }
    }
    val ageFieldData = remember {
        derivedStateOf { Pair(ageFieldState, ageFieldVisibility) }
    }
    val sexFieldData = remember {
        derivedStateOf { Pair(sexFieldState, sexFieldVisibility) }
    }
    val weightFieldData = remember {
        derivedStateOf { Pair(weightFieldState, weightFieldVisibility) }
    }
    val goalFieldData = remember {
        derivedStateOf { Pair(goalFieldState, goalFieldVisibility) }
    }
    val sterilizedFieldData = remember {
        derivedStateOf { Pair(sterilizedFieldState, sterilizedFieldVisibility) }
    }
    val activityFieldData = remember {
        derivedStateOf { Pair(activityFieldState, activityFieldVisibility) }
    }
    val listOfStates = remember {
        derivedStateOf {
            listOf(
                petNameFieldData.value,
                ageFieldData.value,
                sexFieldData.value,
                weightFieldData.value,
                goalFieldData.value,
                activityFieldData.value,
                sterilizedFieldData.value
            )
        }
    }

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
                        onClick = {
                           actionTriggered(0,
                               0,
                               addPetViewmodel,
                               petToBeAdded,
                               formItemsHandler,
                               navigateBack)
                        },
                    ) {
                        Text(text = "Añadir")
                    }
                }
            }
        },
        contentWindowInsets = WindowInsets.safeDrawing
    ) { paddingValues ->
        AddPetContent(
            pet = petToBeAdded,
            listOfStates = listOfStates.value,
            addPetViewmodel = addPetViewmodel,
            formItemsHandler = formItemsHandler,
            onPetChanged = { addPetViewmodel.petChanged(it) },
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
fun AddPetContent(
    modifier: Modifier = Modifier,
    pet: PetModel,
    listOfStates: List<Pair<Int, Boolean>>,
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
                actionTriggered(1, it, addPetViewmodel, pet, formItemsHandler, {})

            },
            modifier = Modifier.weight(0.75f, true)
        )
    }
}

private fun actionTriggered(
    action: Int,
    index: Int,
    addPetViewmodel: AddPetViewmodel,
    petToBeAdded: PetModel,
    formItemsHandler: FormItemsInteractionsHandler,
    navigateBack: () -> Unit
){
    val fields = mutableListOf(
        PET_NAME_FIELD,
        AGE_FIELD,
        SEX_FIELD,
        WEIGHT_FIELD,
        GOAL_FIELD,
        STERILIZED_FIELD,
        ACTIVITY_FIELD
    )
    when(action){
        0 -> onAddClicked(fields, addPetViewmodel, petToBeAdded, formItemsHandler, navigateBack)
        else -> onTrailingIconClicked(index, fields, addPetViewmodel, formItemsHandler)
    }
}

private fun onAddClicked(
    fields: List<Int>,
    addPetViewmodel: AddPetViewmodel,
    petToBeAdded: PetModel,
    formItemsHandler: FormItemsInteractionsHandler,
    navigateBack: () -> Unit
){

    val valid = addPetViewmodel.validatePet()
    var canPetBeAdded = true
    for (i in fields) {
        if (!valid.contains(i)) {
            when (i) {
                SEX_FIELD ->
                    formItemsHandler.allFieldsChanged(i, WAITING)

                STERILIZED_FIELD ->
                    formItemsHandler.allFieldsChanged(i, WAITING)

                else ->{
                    formItemsHandler.allFieldsChanged(i, INVALID)
                    canPetBeAdded = false
                }
            }
        } else formItemsHandler.allFieldsChanged(i, VALID)
    }
    if (canPetBeAdded){
        addPetViewmodel.addPet(petToBeAdded)
        navigateBack()
    }
}

private fun onTrailingIconClicked(
    index: Int,
    fields: List<Int>,
    addPetViewmodel: AddPetViewmodel,
    formItemsHandler: FormItemsInteractionsHandler
){
    formItemsHandler.onItemExpansionChanged(index)
    val valid = addPetViewmodel.validatePet()
    for (i in fields) {
        if (!valid.contains(i)) {
            when (i) {
                SEX_FIELD ->
                    formItemsHandler.onItemStateChanged(i, WAITING)

                STERILIZED_FIELD ->
                    formItemsHandler.onItemStateChanged(i, WAITING)

                else ->
                    formItemsHandler.onItemStateChanged(i, INVALID)
            }
        } else formItemsHandler.onItemStateChanged(i, VALID)

    }
}
















