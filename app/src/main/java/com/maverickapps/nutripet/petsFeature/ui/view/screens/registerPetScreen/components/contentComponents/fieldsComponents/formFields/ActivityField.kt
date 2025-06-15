package com.maverickapps.nutripet.petsFeature.ui.view.screens.registerPetScreen.components.contentComponents.fieldsComponents.formFields

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
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import com.maverickapps.nutripet.core.ui.theme.NeutralLight
import com.maverickapps.nutripet.core.ui.theme.PrimaryLight
import com.maverickapps.nutripet.core.ui.theme.SecondaryDarkest
import com.maverickapps.nutripet.core.ui.theme.dimens

@Composable
fun ActivityField(
    activity: String?,
    fieldState: Int,
    expansionState: Boolean,
    modifier: Modifier = Modifier,
    onTrailingIconClicked: () -> Unit = {},
    onActivityChanged: (String) -> Unit = {}
) {
    val options = listOf("Baja", "Media", "Alta")

    FormField(
        label = "Actividad",
        state = fieldState,
        expanded = expansionState,
        onTrailingIconClicked = { onTrailingIconClicked() },
        modifier = modifier
    ) {
        AnimatedVisibility(
            visible = expansionState,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically(),
            modifier = Modifier.padding(top = MaterialTheme.dimens.small1)
        ) {

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                if (expansionState) {
                SingleChoiceSegmentedButtonRow(modifier = Modifier.fillMaxWidth(0.9f)) {
                    options.forEachIndexed { index, option ->
                        SegmentedButton(
                            shape = SegmentedButtonDefaults.itemShape(index, options.size),
                            selected = (option == activity),
                            onClick = { onActivityChanged(option) },
                            colors = SegmentedButtonDefaults.colors(
                                activeBorderColor = SecondaryDarkest,
                                activeContainerColor = PrimaryLight,
                                activeContentColor = SecondaryDarkest,
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

}