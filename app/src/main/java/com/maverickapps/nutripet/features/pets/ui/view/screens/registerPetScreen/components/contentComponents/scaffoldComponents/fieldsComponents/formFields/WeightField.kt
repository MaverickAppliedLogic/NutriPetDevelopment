package com.maverickapps.nutripet.features.pets.ui.view.screens.registerPetScreen.components.contentComponents.scaffoldComponents.fieldsComponents.formFields

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.maverickapps.nutripet.core.ui.theme.dimens
import com.maverickapps.nutripet.features.pets.ui.view.components.customSlider.CustomSlider

@Composable
fun WeightField(
    weight: Float,
    isEditing: Boolean,
    expansionState: Boolean,
    animal: String,
    fieldState: Int,
    modifier: Modifier,
    stopChangingForEditing: () -> Unit,
    onTrailingIconClicked: () -> Unit = {},
    onWeightChanged: (Float) -> Unit = {}
) {
    var valueRange by remember { mutableStateOf(0f..80f) }
    FormField(
        label = "Peso",
        state = fieldState,
        expanded = expansionState,
        onTrailingIconClicked = { onTrailingIconClicked() },
        modifier = modifier
    ) {
        LaunchedEffect(animal) {
            if(!isEditing){
                when(animal){
                    "dog" -> {
                        valueRange = 0f..80f
                        onWeightChanged(minOf(weight, 80f))
                    }
                    "cat" -> {
                        valueRange = 0f..25f
                        onWeightChanged(minOf(weight, 25f))
                    }
                }
            }
             else{
                when(animal){
                    "dog" -> {
                        valueRange = 0f..80f
                    }
                    "cat" -> {
                        valueRange = 0f..25f
                    }
                }
                stopChangingForEditing()
            }
        }
        AnimatedVisibility(
            visible = expansionState,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically(),
            modifier = Modifier.padding(top = MaterialTheme.dimens.small1)
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(bottom = MaterialTheme.dimens.small1)
                    .fillMaxWidth()
            ) {
               CustomSlider(
                   weight = minOf(weight, valueRange.endInclusive),
                   onWeightChanged = { onWeightChanged(it) },
                   valueRange = valueRange,
                   errorCommitting = false
               )
                Spacer(modifier = Modifier.width(MaterialTheme.dimens.extraSmall1))
                Text(text = "Kg", textAlign = TextAlign.Center)
            }
        }
    }
}