package com.example.feedm.petsFeature.ui.view.screens.addPetScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.feedm.R
import com.example.feedm.core.ui.theme.Neutral
import com.example.feedm.core.ui.theme.NeutralDark
import com.example.feedm.core.ui.theme.NeutralLight
import com.example.feedm.core.ui.theme.Primary
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProgressIndicator(
    progress: Float,
    modifier: Modifier = Modifier) {
    val sliderText = String.format(Locale.getDefault(), "%.0f/7", progress * 10)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(NeutralDark, Neutral)
                )
            )
    ) {
        Column {
            Text(
                text = "Nuevo Compañero",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                color = Primary,
                modifier = Modifier.padding(10.dp)
            )
            Spacer(modifier = Modifier.weight(1f, true))
            Text(
                text = sliderText,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                color = Primary,
                modifier = Modifier.padding(horizontal = 10.dp)
            )
            Slider(
                value = progress,
                onValueChange = { },
                colors = SliderDefaults.colors(
                    thumbColor = Primary,
                    activeTrackColor = Primary,
                    inactiveTrackColor = NeutralLight,
                    activeTickColor = Primary,
                    inactiveTickColor = NeutralLight
                ),
                thumb = {
                    Icon(
                        painter = painterResource(R.mipmap.dog_icon),
                        contentDescription = null,
                        tint = Primary,
                        modifier = Modifier
                            .size(ButtonDefaults.IconSize)
                            .scale(1.5f)
                    )
                },
                valueRange = 0f..0.7f,
            )
            Spacer(modifier = Modifier.weight(1f, true))
        }

    }
}