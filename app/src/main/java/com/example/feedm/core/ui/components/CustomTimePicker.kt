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
import com.example.feedm.ui.view.theme.AlmostWhite
import com.example.feedm.ui.view.theme.Orange
import com.example.feedm.ui.view.theme.OrangeSemiTransparent
import com.example.feedm.ui.view.theme.OrangeSoft
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTimePicker(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    mode: Int = 1,
    modifier: Modifier = Modifier
) {
    val currentTime = Calendar.getInstance()
    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
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
                    .colors(clockDialColor = OrangeSemiTransparent,
                        clockDialSelectedContentColor = Color.White,
                        selectorColor = Orange,
                        timeSelectorSelectedContentColor = Color.Black,
                        clockDialUnselectedContentColor = Color.Black,
                        timeSelectorSelectedContainerColor = Orange,
                        timeSelectorUnselectedContainerColor = OrangeSemiTransparent,
                        periodSelectorSelectedContainerColor = Orange,
                        periodSelectorSelectedContentColor = Color.Black,
                        periodSelectorUnselectedContainerColor = AlmostWhite
                        ),
                layoutType = TimePickerLayoutType.Vertical,
            )

            2 -> TimeInput(
                state = timePickerState,
                colors = TimePickerDefaults
                    .colors(clockDialColor = OrangeSoft,
                        selectorColor = Color.Black,
                        periodSelectorSelectedContainerColor = Orange,
                        periodSelectorUnselectedContainerColor = AlmostWhite,
                        periodSelectorSelectedContentColor = Color.Black,
                        timeSelectorSelectedContentColor = Color.Black,
                        timeSelectorSelectedContainerColor = Orange,
                        timeSelectorUnselectedContainerColor = OrangeSemiTransparent,
                    ),
            )
        }
        Row {
            Button(onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(containerColor = Orange)) {
                Text("Cancel")
            }
            Spacer(Modifier.padding(10.dp))
            Button(onClick = onConfirm,
                colors = ButtonDefaults.buttonColors(containerColor = Orange)) {
                Text("Confirm")
            }
        }
    }

}
@Preview(showBackground = true)
@Composable
fun CustomTimePickerModel1Preview() {
    CustomTimePicker(onConfirm = {},
        onDismiss={})
}

@Preview(showBackground = true)
@Composable
fun CustomTimePickerModel2Preview() {
    CustomTimePicker(onConfirm = {},
        onDismiss={}, mode = 2)
}