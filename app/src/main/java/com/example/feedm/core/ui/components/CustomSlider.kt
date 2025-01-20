package com.example.feedm.core.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.feedm.R
import com.example.feedm.ui.view.theme.Orange
import com.example.feedm.ui.view.theme.OrangeSemiTransparent
import com.example.feedm.ui.view.theme.RedSemiTransparent
import java.util.Locale

@Composable
fun CustomSlider(
    weight: Float,
    onWeightChanged: (Float) -> Unit,
    errorCommitting: Boolean,
    modifier: Modifier = Modifier,
    valueRange: ClosedFloatingPointRange<Float>
) {
    Column(modifier = modifier.padding(start = 15.dp, end = 15.dp, top = 10.dp)) {


        Text(
            stringResource(id = R.string.fa_txtSpinnerPeso),
            style = TextStyle(
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        )
        Text(
            String.format(Locale.getDefault(), "%.2f Kg", weight),
            style = TextStyle(fontSize = 19.sp, color = Color.Black),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 10.dp)
        )
        Slider(
            value = weight,
            onValueChange = { onWeightChanged(it) },
            valueRange = valueRange,
            modifier = Modifier.padding(
                top = 0.dp,
                bottom = 10.dp, start = 10.dp, end = 10.dp
            ), colors = SliderDefaults.colors(
                thumbColor = Color.Transparent,
                activeTrackColor = if (errorCommitting) Color.Red else Orange,
                inactiveTrackColor = if (errorCommitting) RedSemiTransparent else OrangeSemiTransparent
            )
        )
    }
}