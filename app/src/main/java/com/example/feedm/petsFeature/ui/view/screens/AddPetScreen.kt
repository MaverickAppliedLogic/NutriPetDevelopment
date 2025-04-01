package com.example.feedm.petsFeature.ui.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.feedm.R
import com.example.feedm.core.ui.theme.Neutral
import com.example.feedm.core.ui.theme.NeutralDark
import com.example.feedm.core.ui.theme.NeutralLight
import com.example.feedm.core.ui.theme.Primary
import java.util.Locale

@Preview
@Composable
fun AddPetScreen(navToBackStack: () -> Unit = {}) {

    Scaffold(bottomBar = {
        BottomAppBar(containerColor = NeutralLight) {
            Row(
                Modifier
                    .fillMaxSize()
                    .background(Color.Transparent),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    shape = RoundedCornerShape(5.dp),
                    onClick = { /* Acción al presionar el botón */ },
                ) {
                    Text(text = "Añadir")
                }
            }
        }
    }, contentWindowInsets = WindowInsets.safeDrawing) {
        AddPetContent(modifier = Modifier.padding(it))
    }
}

@Composable
fun AddPetContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        ProgressIndicator(modifier = Modifier.weight(0.25f, true))
        Form(modifier = Modifier.weight(0.75f, true))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProgressIndicator(modifier: Modifier = Modifier) {
    var sliderValue by remember { mutableFloatStateOf(0.5f) }
    val sliderText by remember {
        derivedStateOf {
            String.format(Locale.getDefault(), "%.0f/7", sliderValue * 10)
        }
    }

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
                value = sliderValue,
                onValueChange = { sliderValue = it },
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

@Composable
fun Form(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(NeutralLight)
    ) {
        FormField(label = "Nuevo Compañero", modifier = Modifier.weight(1f, true))
        FormField(label = "Edad", modifier = Modifier.weight(1f, true))
        FormField(label = "Sexo (Opcional)", modifier = Modifier.weight(1f, true))
        FormField(label = "Peso", modifier = Modifier.weight(1f, true))
        FormField(label = "Objetivo", modifier = Modifier.weight(1f, true))
        FormField(label = "¿Está esterilizado? (Opcional)", modifier = Modifier.weight(1f, true))
        FormField(label = "Actividad Física", modifier = Modifier.weight(1f, true))
    }
}

@Composable
fun FormField(
    label: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 15.dp, horizontal = 10.dp)
    ) {
        Icon(imageVector = Icons.Default.AddCircle, contentDescription = null)
        Spacer(modifier = Modifier.padding(8.dp))
        Text(text = label, style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.weight(1f))
        Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = null)

    }
    HorizontalDivider()
}

