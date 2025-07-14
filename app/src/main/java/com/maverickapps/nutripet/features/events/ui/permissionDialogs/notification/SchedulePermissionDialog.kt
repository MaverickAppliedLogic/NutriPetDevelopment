package com.maverickapps.nutripet.features.events.ui.permissionDialogs.notification

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.maverickapps.nutripet.core.ui.theme.NeutralDark
import com.maverickapps.nutripet.core.ui.theme.SecondaryDarkest

@Composable
fun SchedulePermissionDialog(
    modifier: Modifier = Modifier,
    schedulePermissionRequest: () -> Unit,
    schedulePermissionDismiss: () -> Unit
){

    AlertDialog(
        title = {
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center) {
                Icon(imageVector = Icons.Default.DateRange,
                    contentDescription = null,
                    modifier = Modifier.size(65.dp))
            }
        },
        text = {
            Column(modifier = Modifier
                .fillMaxHeight()
                .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center){
                Text(text = "¿Permitir alarmas exactas?",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Para recordarte con precisión cuándo alimentar a tu mascota," +
                        " necesitamos permiso para programar alarmas exactas. Esto garantiza que" +
                        " las notificaciones lleguen justo a tiempo, incluso si el dispositivo" +
                        " está en reposo.",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth())
            }
        },
        onDismissRequest = {schedulePermissionDismiss()},
        confirmButton = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement =  Arrangement.Center) {
                Spacer(modifier = Modifier.weight(1f,true))

                Button(
                    onClick = { schedulePermissionDismiss() }) {
                    Text(text = "Denegar")
                }
                Spacer(modifier = Modifier.weight(1f,true))
                Button(onClick = schedulePermissionRequest ) {
                    Text(text = "Permitir")
                }
                Spacer(modifier = Modifier.weight(1f,true))

            }
        },
        containerColor = NeutralDark,
        textContentColor = SecondaryDarkest,
        modifier = modifier
    )
}