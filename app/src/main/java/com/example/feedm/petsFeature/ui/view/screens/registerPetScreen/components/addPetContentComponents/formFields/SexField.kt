package com.example.feedm.petsFeature.ui.view.screens.registerPetScreen.components.addPetContentComponents.formFields

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SexField(
    sex: String?,
    fieldState: Int,
    expansionState: Boolean,
    modifier: Modifier = Modifier,
    onTrailingIconClicked: () -> Unit = {},
    onSexChanged: (String) -> Unit = {}
){
    FormField(
        label = "Sexo (Opcional)",
        state = fieldState,
        expanded = expansionState,
        onTrailingIconClicked = { onTrailingIconClicked() },
        modifier = modifier
    ){
        val options = listOf("Macho", "Hembra")
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
                        RadioButton(selected = (it == sex),
                            onClick = { onSexChanged(it) })
                    }
                }
            }
        }
    }
}