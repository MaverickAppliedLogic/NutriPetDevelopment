package com.example.feedm.petsFeature.ui.view.screens.registerPetScreen.components.contentComponents.fieldsComponents.formFields

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.feedm.core.ui.theme.dimens

@Composable
fun SterilizationField(
    sterilized: Boolean,
    fieldState: Int,
    expansionState: Boolean,
    modifier: Modifier = Modifier,
    onTrailingIconClicked: () -> Unit = {},
    sterilizedChanged: (Boolean) -> Unit = {}
){
    val preparedSelection = if (sterilized) "Si" else "No"
    FormField(
        label = "¿Está esterilizado? (Opcional)",
        state = fieldState,
        expanded = expansionState,
        onTrailingIconClicked = { onTrailingIconClicked() },
        modifier = modifier
    ){
        val options = listOf("Si", "No")
        AnimatedVisibility(
            visible = expansionState,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically(),
            modifier = Modifier.padding(top = MaterialTheme.dimens.small1)
        ){

            Row(Modifier.fillMaxWidth()) {
                options.forEach{
                    Row(verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.weight(1f,true)) {
                        Text(it)
                        RadioButton(selected = (it == preparedSelection),
                            onClick = { sterilizedChanged(it == "Si") })
                    }
                }
            }
        }
    }
}