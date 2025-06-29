package com.maverickapps.nutripet.core.ui.components.dialogs.updateDialogs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maverickapps.nutripet.core.ui.theme.NeutralDark
import com.maverickapps.nutripet.core.ui.theme.Primary
import com.maverickapps.nutripet.core.ui.theme.SecondaryDarkest

@Preview
@Composable
fun NeedToUpdateDialog(
    onDismiss: () -> Unit = {}
) {
    AlertDialog(
        containerColor = NeutralDark,
        textContentColor = SecondaryDarkest,
        onDismissRequest = onDismiss,
        title = {
            Box {
                Text(text = "\uD83D\uDCF2 Nueva versión disponible",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = SecondaryDarkest,
                    modifier = Modifier.blur(3.dp).padding(3.dp)
                )
                Text(text = "\uD83D\uDCF2 Nueva versión disponible",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = Primary,
                    modifier = Modifier.padding(1.dp)
                )
            }
        },
        text = {
            Text(text = "Descargue la nueva versión para ver las mejoras y siga disfrutando la" +
                    " app.\uD83D\uDC3E",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = SecondaryDarkest,
                modifier = Modifier.padding(1.dp)
            )
        },
        confirmButton = {
            TextButton(onClick = {onDismiss()}) {
                Box {
                    Text(text = "Actualizar",
                        color = SecondaryDarkest,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.blur(3.dp).padding(3.dp))

                    Text(text = "Actualizar",
                        color = Primary,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,)
                }
            }
        },
    )

}