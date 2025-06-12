package com.maverickapps.tailyCare.petsFeature.ui.view.components.customSlider

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.maverickapps.tailyCare.core.ui.theme.Primary
import com.maverickapps.tailyCare.core.ui.theme.Secondary

@Composable
fun CustomTrack(
    sliderValue: Float,
    valueRange: ClosedFloatingPointRange<Float>,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(5.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Secondary)
        )
        Box (
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(sliderValue.fractionOfRange(valueRange))
                .background(Primary)
        )
    }
}

private fun Float.fractionOfRange(range: ClosedFloatingPointRange<Float>): Float {
    return (this - range.start) / (range.endInclusive - range.start)
}