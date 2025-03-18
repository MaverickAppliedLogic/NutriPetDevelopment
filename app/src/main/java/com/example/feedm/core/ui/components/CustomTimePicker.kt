package com.example.feedm.core.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.feedm.core.ui.theme.Primary
import com.example.feedm.core.ui.theme.PrimaryLight
import com.example.feedm.core.ui.theme.PrimaryLightest
import com.example.feedm.core.ui.theme.Secondary
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTimePicker(
    onConfirm: (Int,Int) -> Unit,
    onDismiss: () -> Unit,
    hour: Int,
    minute: Int,
    mode: Int = 1,
    modifier: Modifier = Modifier
) {
    val calendar = Calendar.getInstance()
    var initialHour = calendar.get(Calendar.HOUR_OF_DAY)
    var initialMinute = calendar.get(Calendar.MINUTE)

    if (hour != calendar.get(Calendar.HOUR_OF_DAY) ||
        minute != calendar.get(Calendar.MINUTE)){
        initialHour = hour
        initialMinute = minute
    }
    val timePickerState = rememberTimePickerState(
        initialHour = initialHour,
        initialMinute = initialMinute,
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
                    .colors(clockDialColor = PrimaryLight,
                        clockDialSelectedContentColor = Color.White,
                        selectorColor = PrimaryLightest,
                        timeSelectorSelectedContentColor = Color.Black,
                        clockDialUnselectedContentColor = Color.Black,
                        timeSelectorSelectedContainerColor = PrimaryLightest,
                        timeSelectorUnselectedContainerColor = PrimaryLight,
                        periodSelectorSelectedContainerColor = PrimaryLightest,
                        periodSelectorSelectedContentColor = Color.Black,
                        periodSelectorUnselectedContainerColor = Secondary
                        ),
                layoutType = TimePickerLayoutType.Vertical,
            )

            2 -> TimeInput(
                state = timePickerState,
                colors = TimePickerDefaults
                    .colors(clockDialColor = Primary,
                        selectorColor = Color.Black,
                        periodSelectorSelectedContainerColor = PrimaryLightest,
                        periodSelectorUnselectedContainerColor = Secondary,
                        periodSelectorSelectedContentColor = Color.Black,
                        timeSelectorSelectedContentColor = Color.Black,
                        timeSelectorSelectedContainerColor = PrimaryLightest,
                        timeSelectorUnselectedContainerColor = PrimaryLight,
                    ),
            )
        }
        Row {
            Button(onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryLightest)) {
                Text("Cancel")
            }
            Spacer(Modifier.padding(10.dp))
            Button(onClick = {onConfirm(timePickerState.hour,timePickerState.minute)},
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryLightest)) {
                Text("Confirm")
            }
        }
    }

}
@Preview(showBackground = true)
@Composable
fun CustomTimePickerModel1Preview() {
    CustomTimePicker(onConfirm = { _, _ ->},
        onDismiss={}, minute = 0, hour = 0)
}

@Preview(showBackground = true)
@Composable
fun CustomTimePickerModel2Preview() {
    CustomTimePicker(onConfirm = {_, _ ->},
        onDismiss={}, mode = 2, minute = 0, hour = 0)
}