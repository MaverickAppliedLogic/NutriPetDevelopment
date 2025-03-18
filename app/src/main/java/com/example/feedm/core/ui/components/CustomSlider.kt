package com.example.feedm.core.ui.components


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.feedm.R
import com.example.feedm.core.ui.theme.PrimaryDark
import com.example.feedm.core.ui.theme.PrimaryLight
import com.example.feedm.core.ui.theme.PrimaryLightest
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
    Column(modifier = modifier.padding(start = 15.dp, end = 15.dp, top = 10.dp)) {
        val steps = ((valueRange.endInclusive - valueRange.start) / 0.25).toInt() - 1
        Text(
            stringResource(id = R.string.fa_txtSpinnerPeso),
            style = TextStyle(
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        )
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
                .padding(top = 10.dp)
        )
        Slider(
            value = weight,
            onValueChange = { onWeightChanged(it) },
            valueRange = valueRange,
            thumb = { Icon(painter = painterResource(R.drawable.img_dog_illustration),
                contentDescription = "",
                tint = PrimaryLightest,
                modifier = Modifier.size(ButtonDefaults.IconSize))},
            steps = steps,
            colors = SliderDefaults.colors(
                thumbColor = PrimaryLightest,
                inactiveTickColor = Color.Transparent,
                activeTickColor = Color.Transparent,
                activeTrackColor = if (errorCommitting) Color.Red else PrimaryLightest,
                inactiveTrackColor = if (errorCommitting) PrimaryDark else PrimaryLight
            ),
            modifier = Modifier.padding(
                top = 0.dp,
                bottom = 10.dp, start = 10.dp, end = 10.dp
            )
        )
    }
}