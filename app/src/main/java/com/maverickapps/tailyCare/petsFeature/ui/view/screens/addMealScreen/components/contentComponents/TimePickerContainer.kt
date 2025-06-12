package com.maverickapps.tailyCare.petsFeature.ui.view.screens.addMealScreen.components.contentComponents

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.maverickapps.tailyCare.core.ui.theme.NeutralLight
import com.maverickapps.tailyCare.core.ui.theme.SecondaryDarkest
import com.maverickapps.tailyCare.core.ui.theme.dimens
import com.maverickapps.tailyCare.petsFeature.ui.view.components.CustomTimePicker
import java.util.Locale

@Composable
fun TimePickerContainer(
    timePickerIsVisible: Boolean,
    hour: Int,
    min: Int,
    onMealTimeChanged: (Int, Int) -> Unit,
    onTimepickerVisibilityChange: (Boolean) -> Unit,
) {

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(modifier = Modifier
            .padding(top = MaterialTheme.dimens.extraExtraLarge4,
                start = MaterialTheme.dimens.large1,
                end = MaterialTheme.dimens.large1)
            .height(MaterialTheme.dimens.extraLarge1)) {
            Text(
                text = MaterialTheme.dimens.extraLarge1.toString(),
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                color = SecondaryDarkest,
                modifier = Modifier.padding()
            )
            Card(
                onClick = { onTimepickerVisibilityChange(true) },
                enabled = !timePickerIsVisible,
                elevation = CardDefaults.cardElevation(
                    defaultElevation = MaterialTheme.dimens.extraSmall1
                ),
                colors =
                CardDefaults.cardColors(
                    containerColor = NeutralLight, contentColor = SecondaryDarkest,
                    disabledContainerColor = NeutralLight, disabledContentColor = SecondaryDarkest
                ),
                modifier = Modifier.fillMaxSize()

            ) {
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
        val focusManager = LocalFocusManager.current
        val keyboardController = LocalSoftwareKeyboardController
        AnimatedVisibility(
            visible = timePickerIsVisible,
            enter = expandVertically(),
            exit = shrinkVertically()
        ) {
            Card(colors = CardDefaults.cardColors(containerColor = NeutralLight),
                modifier = Modifier
                .height(MaterialTheme.dimens.largest)
                .width(MaterialTheme.dimens.extraExtraLarge3)) {
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
                        .padding(vertical = MaterialTheme.dimens.extraSmall3)
                )
            }
        }
    }
}