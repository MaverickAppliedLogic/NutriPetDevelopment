package com.example.feedm.petsFeature.ui.view.screens.addPetScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.snap
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.feedm.R
import com.example.feedm.core.ui.theme.Error
import com.example.feedm.core.ui.theme.NeutralLight
import com.example.feedm.core.ui.theme.Primary
import com.example.feedm.core.ui.theme.PrimaryDark
import com.example.feedm.core.ui.theme.PrimaryLight
import com.example.feedm.core.ui.theme.SecondaryDarkest
import com.example.feedm.petsFeature.ui.view.components.FormField
import com.example.feedm.petsFeature.ui.view.components.FormFieldStates.VALID
import com.example.feedm.petsFeature.ui.view.screens.addPetScreen.components.ProgressIndicator
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
            Modifier.weight(1f, true)
        )
        FormField(
            label = "Edad",
            state = statesList[1],
            expanded = expansionList[1],
            onTrailingIconClicked = { formItemsHandler.onItemExpansionChanged(1) },
            modifier = Modifier.weight(1f, true)
        )
        FormField(
            label = "Sexo (Opcional)",
            state = statesList[2],
            expanded = expansionList[2],
            onTrailingIconClicked = { formItemsHandler.onItemExpansionChanged(2) },
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
        FormField(
            label = "¿Está esterilizado? (Opcional)",
            state = statesList[5],
            expanded = expansionList[5],
            onTrailingIconClicked = { formItemsHandler.onItemExpansionChanged(5) },
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

@Composable
fun PetNameAndAnimalField(
    expansionState: Boolean,
    fieldState: Int,
    modifier: Modifier
) {
    FormField(
        label = "Nuevo Compañero",
        state = fieldState,
        expanded = expansionState,
        onTrailingIconClicked = { formItemsHandler.onItemExpansionChanged(0) },
        modifier = modifier
    ) {
        /*** Executes a rotation animation for the `Icons.Default.Refresh`
         * whenever the card is clicked.
         * ```
         * When the user clicks the PetCard, the animation begins by updating the 'animal' value.
         * The 'animalChanged' flag is then set to true, disabling the card to prevent further
         * clicks and ensuring the animation isn´t paused in the middle of the rotation.
         * ```
         ***/

        var hasAnimalChanged by remember { mutableStateOf(false) }
        var currentAnimal by remember { mutableStateOf("dog") }
        val rotationAnim by animateFloatAsState(
            targetValue = if (hasAnimalChanged) 360f else 0f,
            label = "",
            animationSpec = if (hasAnimalChanged) tween(500) else snap(0),
            finishedListener = { hasAnimalChanged = false }
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
        ) {
            Spacer(modifier = Modifier.weight(0.1f, true))
            Card(
                elevation = CardDefaults.elevatedCardElevation(3.dp),
                enabled = !hasAnimalChanged,
                colors = CardDefaults.cardColors(
                    containerColor = Primary,
                    contentColor = Color.Transparent,
                    disabledContainerColor = Primary,
                    disabledContentColor = Color.Transparent
                ),
                onClick = {
                    currentAnimal = if (currentAnimal == "dog") "cat" else "dog"
                    hasAnimalChanged = true
                },
                modifier = Modifier
                    .padding(bottom = 15.dp)
                    .weight(0.3f)
                    .align(Alignment.CenterVertically)
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Change animal",
                    tint = Color.Black,
                    modifier = Modifier
                        .scale(0.8f)
                        .align(Alignment.CenterHorizontally)
                        .rotate(rotationAnim)
                )
                val animalImage = if (currentAnimal == "dog") {
                    R.drawable.img_dog_illustration
                } else {
                    R.drawable.icono_gato_sinfondo
                }
                Image(
                    painter = painterResource(id = animalImage),
                    contentDescription = "",
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .fillMaxSize()
                        .padding(bottom = 5.dp)
                )
            }
            Spacer(modifier = Modifier.weight(0.1f))
            AnimatedVisibility(
                visible = expansionState,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically(),
                modifier = Modifier
                    .weight(0.5f)
                    .align(Alignment.CenterVertically)
            ) {
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    label = { Text(text = stringResource(id = R.string.fa_hintEtPetName)) },
                    isError = false,
                    colors = TextFieldDefaults.colors(
                        cursorColor = PrimaryDark,
                        unfocusedContainerColor = PrimaryLight,
                        focusedContainerColor = PrimaryLight,
                        errorContainerColor = Error,
                        unfocusedLabelColor = PrimaryDark,
                        focusedLabelColor = PrimaryDark,
                        errorLabelColor = Error,
                        unfocusedTextColor = PrimaryDark,
                        focusedTextColor = SecondaryDarkest,
                        errorTextColor = Error,
                        focusedIndicatorColor = PrimaryDark,
                        unfocusedIndicatorColor = PrimaryDark,
                        errorIndicatorColor = Error
                    ),
                    modifier = Modifier.padding(bottom = 10.dp)
                )
            }
            Spacer(modifier = Modifier.weight(0.1f, true))
        }
    }
}

