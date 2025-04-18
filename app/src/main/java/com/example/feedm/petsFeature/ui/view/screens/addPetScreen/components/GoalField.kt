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
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import com.example.feedm.core.ui.theme.NeutralDark
import com.example.feedm.core.ui.theme.NeutralLight
import com.example.feedm.core.ui.theme.Primary
import com.example.feedm.core.ui.theme.SecondaryDarkest

@Composable
fun GoalField(
    fieldState: Int,
    expansionState: Boolean,
    modifier: Modifier = Modifier,
    onTrailingIconClicked: () -> Unit = {}
) {
    val options = listOf("Perder peso", "Mantener ", "Ganar peso")
    var selectedOption by remember { mutableStateOf(options[0]) }

    FormField(
        label = "Objetivo",
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
        ){

        Row(horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()){
            SingleChoiceSegmentedButtonRow (modifier = Modifier.fillMaxWidth(0.9f)){
                options.forEachIndexed { index, option ->
                    SegmentedButton(
                        shape = SegmentedButtonDefaults.itemShape(index, options.size),
                        selected = (option == selectedOption),
                        onClick = { selectedOption = option },
                        colors = SegmentedButtonDefaults.colors(
                            activeBorderColor = SecondaryDarkest,
                            activeContainerColor = NeutralDark,
                            activeContentColor = Primary,
                            inactiveBorderColor = SecondaryDarkest,
                            inactiveContainerColor = NeutralLight,
                            inactiveContentColor = SecondaryDarkest

                        ),
                        modifier = Modifier.scale(1f)
                        ) {
                        Text(text = option)
                    }

                }
            }
        }
        }
    }

}