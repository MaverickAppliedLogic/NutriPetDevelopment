package com.example.feedm.petsFeature.ui.view.screens.addPetScreen.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.feedm.petsFeature.ui.view.components.FormField
import com.example.feedm.petsFeature.ui.view.screens.addPetScreen.formItemsHandler

@Composable
fun SexField(
    fieldState: Int,
    expansionState: Boolean,
    modifier: Modifier = Modifier,
){
    FormField(
        label = "Sexo (Opcional)",
        state = fieldState,
        expanded = expansionState,
        onTrailingIconClicked = { formItemsHandler.onItemExpansionChanged(2) },
        modifier = modifier
    ){
        val options = listOf("Macho", "Hembra")
        var selectedOption by remember { mutableStateOf(options[0]) }
        AnimatedVisibility(
            visible = expansionState,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically(),
            modifier = Modifier.padding(top = 15.dp)
        ){

            Row(Modifier.fillMaxWidth()) {
                options.forEach{
                    Row(verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.weight(1f,true)) {
                        Text(it)
                        RadioButton(selected = (it == selectedOption),
                            onClick = { selectedOption = it })
                    }
                }
            }
        }
    }
}