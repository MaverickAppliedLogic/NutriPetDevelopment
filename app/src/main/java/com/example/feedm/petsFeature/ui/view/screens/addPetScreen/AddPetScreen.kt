package com.example.feedm.petsFeature.ui.view.screens.addPetScreen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.feedm.core.ui.theme.NeutralLight
import com.example.feedm.petsFeature.ui.view.screens.addPetScreen.components.ActivityField
import com.example.feedm.petsFeature.ui.view.screens.addPetScreen.components.AgeField
import com.example.feedm.petsFeature.ui.view.screens.addPetScreen.components.FormFieldStates.INVALID
import com.example.feedm.petsFeature.ui.view.screens.addPetScreen.components.FormFieldStates.VALID
import com.example.feedm.petsFeature.ui.view.screens.addPetScreen.components.GoalField
import com.example.feedm.petsFeature.ui.view.screens.addPetScreen.components.PetNameAndAnimalField
import com.example.feedm.petsFeature.ui.view.screens.addPetScreen.components.ProgressIndicator
import com.example.feedm.petsFeature.ui.view.screens.addPetScreen.components.SexField
import com.example.feedm.petsFeature.ui.view.screens.addPetScreen.components.SterilizationField
import com.example.feedm.petsFeature.ui.view.screens.addPetScreen.components.WeightField
import com.example.feedm.petsFeature.ui.view.screens.addPetScreen.utils.FormItemsInteractionsHandler
import com.example.feedm.petsFeature.ui.viewmodel.AddPetViewmodel


private val formItemsHandler = FormItemsInteractionsHandler(7)

@Preview
@Composable
fun AddPetScreen(
    addPetViewmodel: AddPetViewmodel = viewModel(),
    navigateBack: () -> Unit = {}) {

    val petToBeAdded by addPetViewmodel.petToBeAdded.collectAsState()
    val states by formItemsHandler.statesList.collectAsState()
    val expansions by formItemsHandler.expansionList.collectAsState()

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
            states = states,
            expansions = expansions,
            onItemExpansionChanged = { index ->
                formItemsHandler.onItemExpansionChanged(index)
                checkFormValidity(index, addPetViewmodel)
                                     },
            modifier = Modifier.padding(paddingValues))
    }
}

fun checkFormValidity(
    index: Int,
    addPetViewmodel: AddPetViewmodel) {
    when(index){
        0 ->
            if(addPetViewmodel.validatePet() == Pair(false, "Name")){
                formItemsHandler.onItemStateChanged(index, INVALID)
            }
            else formItemsHandler.onItemStateChanged(index, VALID)
        1 ->
            if(addPetViewmodel.validatePet() == Pair(false, "Name")){
                formItemsHandler.onItemStateChanged(index, INVALID)
            }
            else formItemsHandler.onItemStateChanged(index, VALID)
        2 ->
            if(addPetViewmodel.validatePet() == Pair(false, "Name")){
                formItemsHandler.onItemStateChanged(index, INVALID)
            }
            else formItemsHandler.onItemStateChanged(index, VALID)
        3 ->
            if(addPetViewmodel.validatePet() == Pair(false, "Name")){
                formItemsHandler.onItemStateChanged(index, INVALID)
            }
            else formItemsHandler.onItemStateChanged(index, VALID)
        4 ->
            if(addPetViewmodel.validatePet() == Pair(false, "Name")){
                formItemsHandler.onItemStateChanged(index, INVALID)
            }
            else formItemsHandler.onItemStateChanged(index, VALID)
        5 ->
            if(addPetViewmodel.validatePet() == Pair(false, "Name")){
                formItemsHandler.onItemStateChanged(index, INVALID)
            }
            else formItemsHandler.onItemStateChanged(index, VALID)
        6 ->
            if(addPetViewmodel.validatePet() == Pair(false, "Name")){
                formItemsHandler.onItemStateChanged(index, INVALID)
            }
            else formItemsHandler.onItemStateChanged(index, VALID)
    }
}

@Composable
fun AddPetContent(
    states: List<Int>,
    expansions: List<Boolean>,
    onItemExpansionChanged: (Int) -> Unit,
    modifier: Modifier = Modifier) {
    val progress by animateFloatAsState(
        targetValue = states.count { it == VALID }.toFloat().div(10),
        animationSpec = tween(500),
        label = ""
    )
    Column(modifier = modifier.fillMaxSize()) {
        ProgressIndicator(
            progress = progress,
            modifier = Modifier.weight(0.25f, true)
        )
        Form(
            statesList = states,
            expansionList = expansions,
            onItemExpansionChanged = { onItemExpansionChanged(it) },
            modifier = Modifier.weight(0.75f, true)
        )
    }
}

@Composable
fun Form(
    statesList: List<Int>,
    expansionList: List<Boolean>,
    onItemExpansionChanged: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(NeutralLight)
    ) {
        PetNameAndAnimalField(
            expansionState = expansionList[0],
            fieldState = statesList[0],
            modifier = Modifier.weight(1f, true),
            onTrailingIconClicked = { onItemExpansionChanged(0) }
        )
        AgeField(
            fieldState = statesList[1],
            expansionState = expansionList[1],
            modifier = Modifier.weight(1f, true),
            onTrailingIconClicked = { onItemExpansionChanged(1) }
        )
        SexField(
            fieldState = statesList[2],
            expansionState = expansionList[2],
            modifier = Modifier.weight(1f, true),
                    onTrailingIconClicked = { onItemExpansionChanged(2) }

        )
        WeightField(
            fieldState = statesList[3],
            expansionState = expansionList[3],
            modifier = Modifier.weight(1f, true),
                    onTrailingIconClicked = { onItemExpansionChanged(3) }

        )
        GoalField(
            fieldState = statesList[4],
            expansionState = expansionList[4],
            modifier = Modifier.weight(1f, true),
            onTrailingIconClicked = { onItemExpansionChanged(4) }

        )
        SterilizationField(
            fieldState = statesList[5],
            expansionState = expansionList[5],
            modifier = Modifier.weight(1f, true),
            onTrailingIconClicked = { onItemExpansionChanged(5) }

        )
        ActivityField(
            fieldState = statesList[6],
            expansionState = expansionList[6],
            modifier = Modifier.weight(1f, true),
            onTrailingIconClicked = { onItemExpansionChanged(6) }
        )
    }
}











