package com.example.feedm.petsFeature.ui.view.screens.addMealScreen.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.feedm.core.ui.theme.NeutralLight
import com.example.feedm.core.ui.theme.SecondaryDarkest
import com.example.feedm.petsFeature.ui.view.components.CustomTimePicker
import java.util.Locale

@Composable
fun TimePickerContainer(
    timePickerIsVisible: Boolean,
    hour: Int,
    min: Int,
    onMealTimeChanged: (Int,Int) -> Unit,
    onTimepickerVisibilityChange: (Boolean) -> Unit,
){
    val timePickerPaddingTopAnim by animateDpAsState(
        targetValue = if (timePickerIsVisible) 200.dp else { 570.dp },
        animationSpec = spring(dampingRatio = 0.7f, stiffness = 150f),
        label = "TimePickerAnimation"
    )
    val timePickerPaddingBottomAnim by animateDpAsState(
        targetValue = if (timePickerIsVisible) 170.dp else { 200.dp },
        animationSpec = tween(),
        label = "TimePickerAnimation"
    )
    val timePickerHorizontalPaddingAnim by animateDpAsState(
        targetValue = if (timePickerIsVisible) 30.dp else 60.dp,
    )
    Card(
        onClick = { onTimepickerVisibilityChange(true) },
        enabled = !timePickerIsVisible,
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        colors =
        CardDefaults.cardColors(containerColor = NeutralLight, contentColor = SecondaryDarkest,
            disabledContainerColor = NeutralLight, disabledContentColor = SecondaryDarkest),
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = timePickerPaddingTopAnim,
                bottom = timePickerPaddingBottomAnim,
                start = timePickerHorizontalPaddingAnim,
                end = timePickerHorizontalPaddingAnim
            )
    ) {
        val focusManager = LocalFocusManager.current
        val keyboardController = LocalSoftwareKeyboardController
        if (timePickerIsVisible) {
            focusManager.clearFocus()
            keyboardController.current?.hide()
            CustomTimePicker(
                hour = hour,
                minute = min,
                onConfirm = { hourSet, minSet ->
                    onTimepickerVisibilityChange(false)
                    onMealTimeChanged(hourSet, minSet)
                },
                onDismiss = { onTimepickerVisibilityChange(false) },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 10.dp)
            )
        } else {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = String.format(Locale.getDefault(), "%02d:%02d", hour, min),
                    textAlign = TextAlign.Center,
                    letterSpacing = 2.sp,
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center)
                )
            }
        }
    }
}