package com.maverickapps.tailyCare.petsFeature.ui.view.components.customSlider


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomSlider(
    weight: Float,
    onWeightChanged: (Float) -> Unit,
    errorCommitting: Boolean,
    modifier: Modifier = Modifier,
    valueRange: ClosedFloatingPointRange<Float>
) {
    Column(modifier = modifier.padding(start = 15.dp, end = 15.dp)) {
        val steps = ((valueRange.endInclusive - valueRange.start) / 0.5).toInt() - 1
        val text = if (valueRange.endInclusive != weight){
            String.format(Locale.getDefault(), "%.2f Kg", weight)
        }
        else {
            String.format((Locale.getDefault()), "+%.2f Kg", weight)
        }
        Text(text = text,
            style = TextStyle(fontSize = 19.sp, color = Color.Black),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 10.dp)
        )
        Slider(
            value = weight,
            onValueChange = { onWeightChanged(it) },
            valueRange = valueRange,
            steps = steps,
            track = { slider ->
                CustomTrack(
                    sliderValue = slider.value,
                    valueRange = valueRange,
                    modifier = Modifier
                )
            },
            modifier = Modifier.padding(
                top = 0.dp,
                bottom = 10.dp, start = 10.dp, end = 10.dp
            ).height(10.dp)
        )
    }
}