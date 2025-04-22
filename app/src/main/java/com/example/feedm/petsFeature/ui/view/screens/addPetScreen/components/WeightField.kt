package com.example.feedm.petsFeature.ui.view.screens.addPetScreen.components

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.feedm.petsFeature.ui.view.components.customSlider.CustomSlider

@Composable
fun WeightField(
    weight: Float,
    expansionState: Boolean,
    fieldState: Int,
    modifier: Modifier,
    onTrailingIconClicked: () -> Unit = {},
    onWeightChanged: (Float) -> Unit = {}
) {
    FormField(
        label = "Peso",
        state = fieldState,
        expanded = expansionState,
        onTrailingIconClicked = { onTrailingIconClicked() },
        modifier = modifier
    ) {
        AnimatedVisibility(
            visible = expansionState,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically(),
            modifier = Modifier.padding(top = 15.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(bottom = 15.dp)
                    .fillMaxWidth()
            ) {
               CustomSlider(
                   weight = weight,
                   onWeightChanged = { onWeightChanged(it) },
                   valueRange = 0f..80f,
                   errorCommitting = false
               )
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "Kg", textAlign = TextAlign.Center)
            }
        }
    }
}