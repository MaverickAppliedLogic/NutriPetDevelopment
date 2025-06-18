package com.maverickapps.nutripet.petsFeature.ui.view.screens.addMealScreen.components.contentComponents


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import com.maverickapps.nutripet.core.ui.theme.dimens
import com.maverickapps.nutripet.petsFeature.utils.timeFormatter.TimeFormatter

@Composable
fun MealTimePicker(
    timePickerIsVisible: Boolean,
    mealTime: Long,
    onMealTimeChanged: (Long) -> Unit,
    onTimepickerVisibilityChange: (Boolean) -> Unit,
) {
    val timeFormatter = TimeFormatter()
    val hour = timeFormatter.formatMillsToInt(mealTime).first
    val min = timeFormatter.formatMillsToInt(mealTime).second

    LaunchedEffect(true) {
        onMealTimeChanged(timeFormatter.currentTime)
    }
    Spacer(modifier = Modifier.height(MaterialTheme.dimens.extraSmall2))
    if (timePickerIsVisible) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .alpha(0f)
        ) {
            Card(
                onClick = { onTimepickerVisibilityChange(false) },
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(0f)
            ) { }
        }
    }
    TimePickerContainer(
        timePickerIsVisible = timePickerIsVisible,
        hour = hour,
        min = min,
        onMealTimeChanged = { hourSet, minSet ->
            println("HourSet: $hourSet")
            println("MinSet: $minSet")
            onMealTimeChanged(timeFormatter.formatIntToMills(hourSet, minSet, 0, 0))},
        onTimepickerVisibilityChange = { onTimepickerVisibilityChange(it) }
    )
}