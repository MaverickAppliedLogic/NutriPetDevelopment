package com.example.feedm.petsFeature.ui.view.screens.addPetScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.feedm.core.ui.theme.NeutralLight
import com.example.feedm.petsFeature.domain.objectTasks.pet.model.PetModel
import com.example.feedm.petsFeature.ui.view.screens.addPetScreen.components.ActivityField
import com.example.feedm.petsFeature.ui.view.screens.addPetScreen.components.AgeField
import com.example.feedm.petsFeature.ui.view.screens.addPetScreen.components.FormFieldStates.INVALID
import com.example.feedm.petsFeature.ui.view.screens.addPetScreen.components.FormFieldStates.VALID
import com.example.feedm.petsFeature.ui.view.screens.addPetScreen.components.FormFieldStates.WAITING
import com.example.feedm.petsFeature.ui.view.screens.addPetScreen.components.GoalField
import com.example.feedm.petsFeature.ui.view.screens.addPetScreen.components.PetNameAndAnimalField
import com.example.feedm.petsFeature.ui.view.screens.addPetScreen.components.ProgressIndicator
import com.example.feedm.petsFeature.ui.view.screens.addPetScreen.components.SexField
import com.example.feedm.petsFeature.ui.view.screens.addPetScreen.components.SterilizationField
import com.example.feedm.petsFeature.ui.view.screens.addPetScreen.components.WeightField
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
    val petToBeAdded by addPetViewmodel.petToBeAdded.collectAsState()
    // Colectores para FieldState
    val petNameFieldState by formItemsHandler.petNameFieldState.collectAsState()
    val ageFieldState by formItemsHandler.ageFieldState.collectAsState()
    val sexFieldState by formItemsHandler.sexFieldState.collectAsState()
    val weightFieldState by formItemsHandler.weightFieldState.collectAsState()
    val goalFieldState by formItemsHandler.goalFieldState.collectAsState()
    val sterilizedFieldState by formItemsHandler.sterilizedFieldState.collectAsState()
    val activityFieldState by formItemsHandler.activityFieldState.collectAsState()

    // Colectores para FieldVisibility
    val petNameFieldVisibility by formItemsHandler.petNameFieldVisibility.collectAsState()
    val ageFieldVisibility by formItemsHandler.ageFieldVisibility.collectAsState()
    val sexFieldVisibility by formItemsHandler.sexFieldVisibility.collectAsState()
    val weightFieldVisibility by formItemsHandler.weightFieldVisibility.collectAsState()
    val goalFieldVisibility by formItemsHandler.goalFieldVisibility.collectAsState()
    val sterilizedFieldVisibility by formItemsHandler.sterilizedFieldVisibility.collectAsState()
    val activityFieldVisibility by formItemsHandler.activityField.collectAsState()

    // Uso de derivedStateOf para evitar recomposición innecesaria
    val petNameFieldData = remember {
        derivedStateOf { Pair(petNameFieldState, petNameFieldVisibility) } }
    val ageFieldData = remember {
        derivedStateOf { Pair(ageFieldState, ageFieldVisibility) } }
    val sexFieldData = remember {
        derivedStateOf { Pair(sexFieldState, sexFieldVisibility) } }
    val weightFieldData = remember {
        derivedStateOf { Pair(weightFieldState, weightFieldVisibility) } }
    val goalFieldData = remember {
        derivedStateOf { Pair(goalFieldState, goalFieldVisibility) } }
    val sterilizedFieldData = remember {
        derivedStateOf { Pair(sterilizedFieldState, sterilizedFieldVisibility) } }
    val activityFieldData = remember {
        derivedStateOf { Pair(activityFieldState, activityFieldVisibility) } }
    val listOfStates = remember { derivedStateOf { listOf(
        petNameFieldData.value,
        ageFieldData.value,
        sexFieldData.value,
        weightFieldData.value,
        goalFieldData.value,
        sterilizedFieldData.value,
        activityFieldData.value
    )}}

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
                        onClick = {},
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
                val fields = mutableListOf(
                    PET_NAME_FIELD,
                    AGE_FIELD,
                    SEX_FIELD,
                    WEIGHT_FIELD,
                    GOAL_FIELD,
                    STERILIZED_FIELD,
                    ACTIVITY_FIELD
                )
                formItemsHandler.onItemExpansionChanged(it)
                val valid = addPetViewmodel.validatePet()
                for (i in fields) {
                    if (!valid.contains(i)) {
                        when(i){
                            SEX_FIELD ->
                                formItemsHandler.onItemStateChanged(i, WAITING)
                            STERILIZED_FIELD ->
                                formItemsHandler.onItemStateChanged(i, WAITING)
                            else ->
                                formItemsHandler.onItemStateChanged(i, INVALID)
                        }
                    }
                    else formItemsHandler.onItemStateChanged(i, VALID)

                }

            },
            modifier = Modifier.weight(0.75f, true)
        )
    }
}


@Composable
fun Form(
    pet: PetModel,
    petNameFieldData: Pair<Int, Boolean>,
    ageFieldData: Pair<Int, Boolean>,
    sexFieldData: Pair<Int, Boolean>,
    weightFieldData: Pair<Int, Boolean>,
    goalFieldData: Pair<Int, Boolean>,
    sterilizedFieldData: Pair<Int, Boolean>,
    activityFieldData: Pair<Int, Boolean>,
    modifier: Modifier = Modifier,
    onPetChanged: (PetModel) -> Unit = {},
    onTrailingIconClicked: (Int) -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(NeutralLight)
    ) {
        PetNameAndAnimalField(
            animal = pet.animal,
            name = pet.petName,
            expansionState = petNameFieldData.second,
            fieldState = petNameFieldData.first,
            modifier = Modifier.weight(1f, true),
            onTrailingIconClicked = {
                onTrailingIconClicked(PET_NAME_FIELD)
            },
            onNameChanged = { onPetChanged(pet.copy(petName = it)) },
            onAnimalChanged = { onPetChanged(pet.copy(animal = it)) }
        )

        AgeField(
            age = pet.age,
            expansionState = ageFieldData.second,
            fieldState = ageFieldData.first,
            modifier = Modifier.weight(1f, true),
            onTrailingIconClicked = {
                onTrailingIconClicked(AGE_FIELD)
            },
            onAgeChanged = { onPetChanged(pet.copy(age = it)) }
        )

        SexField(
            sex = pet.genre,
            expansionState = sexFieldData.second,
            fieldState = sexFieldData.first,
            modifier = Modifier.weight(1f, true),
            onTrailingIconClicked = {
                onTrailingIconClicked(SEX_FIELD)
            },
            onSexChanged = { onPetChanged(pet.copy(genre = it)) }
        )

        WeightField(
            weight = pet.petWeight,
            expansionState = weightFieldData.second,
            fieldState = weightFieldData.first,
            modifier = Modifier.weight(1f, true),
            onTrailingIconClicked = {
                onTrailingIconClicked(WEIGHT_FIELD)
            },
            onWeightChanged = { onPetChanged(pet.copy(petWeight = it)) }
        )

        GoalField(
            goal = pet.goal,
            expansionState = goalFieldData.second,
            fieldState = goalFieldData.first,
            modifier = Modifier.weight(1f, true),
            onTrailingIconClicked = {
                onTrailingIconClicked(GOAL_FIELD)
            },
            onGoalChanged = { onPetChanged(pet.copy(goal = it)) }
        )

        ActivityField(
            activity = pet.activity,
            expansionState = activityFieldData.second,
            fieldState = activityFieldData.first,
            modifier = Modifier.weight(1f, true),
            onTrailingIconClicked = {
                onTrailingIconClicked(ACTIVITY_FIELD)
            },
            onActivityChanged = { onPetChanged(pet.copy(activity = it)) }
        )
        SterilizationField(
            sterilized = pet.sterilized,
            expansionState = sterilizedFieldData.second,
            fieldState = sterilizedFieldData.first,
            modifier = Modifier.weight(1f, true),
            onTrailingIconClicked = {
                onTrailingIconClicked(STERILIZED_FIELD)
            },
            sterilizedChanged = { onPetChanged(pet.copy(sterilized = it)) }
        )

    }
}















