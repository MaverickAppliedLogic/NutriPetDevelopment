package com.maverickapps.nutripet.petsFeature.ui.view.components.alerts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maverickapps.nutripet.core.ui.theme.PrimaryLight
import com.maverickapps.nutripet.core.ui.theme.SecondaryDarkest

@Preview
@Composable
fun UpdateNotesDialog(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit = {}
) {
    AlertDialog(
        containerColor = PrimaryLight,
        textContentColor = SecondaryDarkest,
        onDismissRequest = onDismiss,
        title = {
            Text(text = "🔔 Novedades de esta versión",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
            )
        },
        text = {
            Column(modifier = modifier.verticalScroll(rememberScrollState())) {
                Text("🛠️ Correcciones",
                    style = MaterialTheme.typography.titleMedium,
                    color = SecondaryDarkest)
                Spacer(Modifier.height(4.dp))
                Text(
                    "- Se corrigió un error en el texto al añadir comida, " +
                            "que mostraba la densidad de pantalla en lugar de la palabra \"Hora\".",

                    color = SecondaryDarkest
                )
                Text(
                    "- Al editar una mascota, ya no se modifica automáticamente el peso: " +
                        "ahora se mantiene correctamente el valor anterior.",
                    color = SecondaryDarkest
                )
                Text(
                    "- Se solucionó un problema que impedía seleccionar la opción “Senior” " +
                            "al definir la etapa de vida.",
                    color = SecondaryDarkest
                )
                Text(
                    "- Ahora, al editar una mascota, se asigna correctamente el tipo de" +
                            " animal seleccionado.",
                    color = SecondaryDarkest
                )
                Spacer(Modifier.height(12.dp))
                Text("✨ Mejoras",
                    style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.height(4.dp))
                Text(
                    "- Cuando se exceden las calorías recomendadas, el valor ahora se" +
                        " muestra en rojo como advertencia visual para el usuario.",
                    color = SecondaryDarkest
                )
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Aceptar")
            }
        },
    )
}
