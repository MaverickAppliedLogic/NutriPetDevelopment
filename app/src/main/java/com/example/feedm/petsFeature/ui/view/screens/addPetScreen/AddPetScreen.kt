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
import com.example.feedm.core.ui.theme.NeutralLight
import com.example.feedm.petsFeature.ui.view.components.FormField
import com.example.feedm.petsFeature.ui.view.components.FormFieldStates.VALID
import com.example.feedm.petsFeature.ui.view.screens.addPetScreen.components.AgeField
import com.example.feedm.petsFeature.ui.view.screens.addPetScreen.components.PetNameAndAnimalField
import com.example.feedm.petsFeature.ui.view.screens.addPetScreen.components.ProgressIndicator
import com.example.feedm.petsFeature.ui.view.screens.addPetScreen.components.SexField
import com.example.feedm.petsFeature.ui.view.screens.addPetScreen.components.SterilizationField
import com.example.feedm.petsFeature.ui.view.screens.addPetScreen.utils.FormItemsInteractionsHandler


val formItemsHandler = FormItemsInteractionsHandler(7)

@Preview
@Composable
fun AddPetScreen(navigateBack: () -> Unit = {}) {
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
                        onClick = { /* Acción al presionar el botón */ },
                    ) {
                        Text(text = "Añadir")
                    }
                }
            }
        },
        contentWindowInsets = WindowInsets.safeDrawing
    ) {
        AddPetContent(modifier = Modifier.padding(it))
    }
}

@Composable
fun AddPetContent(modifier: Modifier = Modifier) {
    val states by formItemsHandler.statesList.collectAsState()
    val expansions by formItemsHandler.expansionList.collectAsState()
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
            formItemsHandler = formItemsHandler,
            statesList = states,
            expansionList = expansions,
            modifier = Modifier.weight(0.75f, true)
        )
    }
}

@Composable
fun Form(
    formItemsHandler: FormItemsInteractionsHandler,
    statesList: List<Int>,
    expansionList: List<Boolean>,
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
            modifier = Modifier.weight(1f, true)
        )
        AgeField(
            fieldState = statesList[1],
            expansionState = expansionList[1],
            modifier = Modifier.weight(1f, true)
        )
        SexField(
            fieldState = statesList[2],
            expansionState = expansionList[2],
            modifier = Modifier.weight(1f, true)
        )
        FormField(
            label = "Peso",
            state = statesList[3],
            expanded = expansionList[3],
            onTrailingIconClicked = { formItemsHandler.onItemExpansionChanged(3) },
            modifier = Modifier.weight(1f, true)
        )
        FormField(
            label = "Objetivo",
            state = statesList[4],
            expanded = expansionList[4],
            onTrailingIconClicked = { formItemsHandler.onItemExpansionChanged(4) },
            modifier = Modifier.weight(1f, true)
        )
        SterilizationField(
            fieldState = statesList[5],
            expansionState = expansionList[5],
            modifier = Modifier.weight(1f, true)
        )
        FormField(
            label = "Actividad Física",
            state = statesList[6],
            expanded = expansionList[6],
            onTrailingIconClicked = { formItemsHandler.onItemExpansionChanged(6) },
            modifier = Modifier.weight(1f, true)
        )
    }

}










