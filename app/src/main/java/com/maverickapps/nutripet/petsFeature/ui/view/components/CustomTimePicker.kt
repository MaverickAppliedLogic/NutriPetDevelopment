package com.maverickapps.nutripet.petsFeature.ui.view.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.TimePickerLayoutType
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.maverickapps.nutripet.core.ui.theme.Neutral
import com.maverickapps.nutripet.core.ui.theme.NeutralLight
import com.maverickapps.nutripet.core.ui.theme.Primary
import com.maverickapps.nutripet.core.ui.theme.PrimaryLight
import com.maverickapps.nutripet.core.ui.theme.Secondary
import com.maverickapps.nutripet.core.ui.theme.SecondaryDarkest
import com.maverickapps.nutripet.core.ui.theme.dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTimePicker(
    modifier: Modifier = Modifier,
    onConfirm: (Int,Int) -> Unit,
    onDismiss: () -> Unit,
    hour: Int,
    minute: Int,
    mode: Int = 1
) {

    val timePickerState = rememberTimePickerState(
        initialHour = hour,
        initialMinute = minute,
        is24Hour = false
    )
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (mode) {
            1 -> TimePicker(
                state = timePickerState,
                colors = TimePickerDefaults
                    .colors(
                        selectorColor = Primary,
                        clockDialColor = Neutral,
                        clockDialSelectedContentColor = SecondaryDarkest,
                        clockDialUnselectedContentColor = SecondaryDarkest,
                        timeSelectorSelectedContentColor = SecondaryDarkest,
                        timeSelectorSelectedContainerColor = Primary,
                        timeSelectorUnselectedContainerColor = PrimaryLight,
                        timeSelectorUnselectedContentColor = Secondary,
                        periodSelectorSelectedContainerColor = Primary,
                        periodSelectorSelectedContentColor = SecondaryDarkest,
                        periodSelectorUnselectedContainerColor = NeutralLight,
                        periodSelectorUnselectedContentColor = Secondary
                        ),
                layoutType = TimePickerLayoutType.Vertical,
            )

            2 -> TimeInput(
                state = timePickerState,
                colors = TimePickerDefaults
                    .colors(clockDialColor = PrimaryLight,
                        selectorColor = Color.Black,
                        periodSelectorSelectedContainerColor = PrimaryLight,
                        periodSelectorUnselectedContainerColor = NeutralLight,
                        periodSelectorSelectedContentColor = Color.Black,
                        timeSelectorSelectedContentColor = Color.Black,
                        timeSelectorSelectedContainerColor = Primary,
                        timeSelectorUnselectedContainerColor = PrimaryLight,
                    ),
            )
        }
        Row {
            Button(onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(containerColor = Primary)) {
                Text("Cancel")
            }
            Spacer(Modifier.padding(MaterialTheme.dimens.small1))
            Button(onClick = {onConfirm(timePickerState.hour,timePickerState.minute)},
                colors = ButtonDefaults.buttonColors(containerColor = Primary)) {
                Text("Confirm")
            }
        }
    }

}

