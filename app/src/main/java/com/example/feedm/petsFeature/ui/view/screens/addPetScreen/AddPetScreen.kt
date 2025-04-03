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
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.feedm.R
import com.example.feedm.core.ui.theme.NeutralLight
import com.example.feedm.petsFeature.ui.view.components.FormField
import com.example.feedm.petsFeature.ui.view.components.FormFieldStates.VALID
import com.example.feedm.petsFeature.ui.view.screens.addPetScreen.components.ProgressIndicator
import com.example.feedm.petsFeature.ui.view.screens.addPetScreen.utils.FormItemsInteractionsHandler

val formItemsInteractionsHandler =  FormItemsInteractionsHandler(7)

@Preview
@Composable
fun AddPetScreen(navToBackStack: () -> Unit = {}) {

    Scaffold(bottomBar = {
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
    }, contentWindowInsets = WindowInsets.safeDrawing){
        AddPetContent(modifier = Modifier.padding(it))
    }
}

@Composable
fun AddPetContent(modifier: Modifier = Modifier) {
    val statesList by formItemsInteractionsHandler.statesList.collectAsState()
    val expansionList by formItemsInteractionsHandler.expansionList.collectAsState()
    val progress by animateFloatAsState(
        targetValue = statesList.count{it == VALID}.toFloat().div(10),
        animationSpec = tween(500),
        label = ""
    )
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {

        ProgressIndicator(
            progress = progress,
            modifier = Modifier.weight(0.25f, true))
        Form(
            formItemsInteractionsHandler = formItemsInteractionsHandler,
            statesList = statesList,
            expansionList = expansionList,
            modifier = Modifier.weight(0.75f, true))
    }
}



@Composable
fun Form(
    formItemsInteractionsHandler: FormItemsInteractionsHandler,
    statesList: List<Int>,
    expansionList: List<Boolean>,
    onStatesChanged: (Float) -> Unit = {},
    modifier: Modifier = Modifier) {



    Column(
        modifier = modifier
            .fillMaxSize()
            .background(NeutralLight)
    ) {
        FormField(label = "Nuevo Compañero", state = statesList[0],
            expanded = expansionList[0],
            onTrailingIconClicked = {
                formItemsInteractionsHandler.onItemExpansionChanged(0)
                formItemsInteractionsHandler.onItemStateChanged(0, VALID)
            },
            modifier = Modifier.weight(1f, true)){
            Icon(painter = painterResource(R.mipmap.dog_icon), contentDescription = null)
        }
        FormField(label = "Edad", state = statesList[1],
            expanded = expansionList[1],
            onTrailingIconClicked = {
                formItemsInteractionsHandler.onItemExpansionChanged(1)
                formItemsInteractionsHandler.onItemStateChanged(1, VALID)
            },
            modifier = Modifier.weight(1f, true))

        FormField(label = "Sexo (Opcional)", state = statesList[2],
            expanded = expansionList[2],
            onTrailingIconClicked = {
                formItemsInteractionsHandler.onItemExpansionChanged(2)
            },
            modifier = Modifier.weight(1f, true))

        FormField(label = "Peso", state = statesList[3],
            expanded = expansionList[3],
            onTrailingIconClicked = {
                formItemsInteractionsHandler.onItemExpansionChanged(3)
                                    },
            modifier = Modifier.weight(1f, true))

        FormField(label = "Objetivo", state = statesList[4],
            expanded = expansionList[4],
            onTrailingIconClicked = {
                formItemsInteractionsHandler.onItemExpansionChanged(4)
            },
            modifier = Modifier.weight(1f, true))

        FormField(label = "¿Está esterilizado? (Opcional)", state = statesList[5],
            expanded = expansionList[5],
            onTrailingIconClicked = {
                formItemsInteractionsHandler.onItemExpansionChanged(5)
            },
            modifier = Modifier.weight(1f, true))

        FormField(label = "Actividad Física", state = statesList[6],
            expanded = expansionList[6],
            onTrailingIconClicked = {
                formItemsInteractionsHandler.onItemExpansionChanged(6)
            },
            modifier = Modifier.weight(1f, true))
    }
}



