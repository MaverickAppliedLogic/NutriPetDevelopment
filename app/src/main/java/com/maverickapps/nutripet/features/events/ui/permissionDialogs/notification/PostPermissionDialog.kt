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
import androidx.compose.material.icons.filled.Notifications
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
fun PostPermissionDialog(
    modifier: Modifier = Modifier,
    postPermissionDismiss: () -> Unit,
    postPermissionRequest: () -> Unit
) {
    AlertDialog(
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = null,
                    modifier = Modifier.size(65.dp)
                )
            }
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "¿Permitir notificaciones?",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Necesitamos tu permiso para enviarte recordatorios importantes," +
                            " como las horas de comida de tu mascota.",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        onDismissRequest = {},
        confirmButton = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement =  Arrangement.Center) {
                Spacer(modifier = Modifier.weight(1f,true))

                Button(
                    onClick = { postPermissionDismiss() }) {
                    Text(text = "Denegar")
                }
                Spacer(modifier = Modifier.weight(1f,true))
                Button(onClick = postPermissionRequest) {
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