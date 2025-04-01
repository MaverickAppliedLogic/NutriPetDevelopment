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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.feedm.core.ui.theme.Neutral
import com.example.feedm.core.ui.theme.NeutralLight

@Preview
@Composable
fun AddPetScreen(navToBackStack: () -> Unit = {}) {

    Scaffold(bottomBar = {
        BottomAppBar(containerColor = NeutralLight) {
            Row(
                Modifier.fillMaxSize().background(Color.Transparent),
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
        AddCompanionScreen(modifier = Modifier.padding(it))
    }
}

@Composable
fun AddCompanionScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        ProgressIndicator(modifier = Modifier.weight(0.25f, true))
        Form(modifier = Modifier.weight(0.75f, true))
    }
}

@Composable
fun ProgressIndicator(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Neutral)
    ) {
        Text(
            text = "Nuevo Compañero",
            style = MaterialTheme.typography.titleMedium,
            color = Color(0xFFFFA000) // Color personalizado
        )
    }
}

@Composable
fun Form(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(NeutralLight)
    ) {
        FormField(modifier = Modifier.weight(1f, true), "Nuevo Compañero")
        FormField(modifier = Modifier.weight(1f, true), "Edad")
        FormField(modifier = Modifier.weight(1f, true), "Sexo (Opcional)")
        FormField(modifier = Modifier.weight(1f, true), "Peso")
        FormField(modifier = Modifier.weight(1f, true), "Objetivo")
        FormField(modifier = Modifier.weight(1f, true), "¿Está esterilizado? (Opcional)")
        FormField(modifier = Modifier.weight(1f, true), "Actividad Física")
    }
}

@Composable
fun FormField(
    modifier: Modifier = Modifier, label: String
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

