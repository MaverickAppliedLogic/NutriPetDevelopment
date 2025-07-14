package com.maverickapps.nutripet.core.ui.components.dialogs.updateDialogs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maverickapps.nutripet.core.ui.components.BulletPoint
import com.maverickapps.nutripet.core.ui.theme.NeutralDark
import com.maverickapps.nutripet.core.ui.theme.Primary
import com.maverickapps.nutripet.core.ui.theme.SecondaryDarkest
import com.maverickapps.nutripet.core.ui.theme.dimens

@Preview
@Composable
fun UpdateNotesDialog(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit = {}
) {
        AlertDialog(
            containerColor = NeutralDark,
            textContentColor = SecondaryDarkest,
            onDismissRequest = onDismiss,
            title = {
                Box {
                    Text(text = "🔔 Novedades de esta versión",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = SecondaryDarkest,
                        modifier = Modifier.blur(3.dp).padding(3.dp)
                    )
                    Text(text = "🔔 Novedades de esta versión",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = Primary,
                        modifier = Modifier.padding(1.dp)
                    )
                }
            },
            text = {
                Column(modifier = modifier.verticalScroll(rememberScrollState())) {
                    Box {
                        Text("Correcciones \uD83E\uDE9B",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            color = Primary,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth().blur(3.dp))
                        Text("Correcciones \uD83E\uDE9B",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            color = Primary,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth())
                    }
                    Spacer(Modifier.height(MaterialTheme.dimens.small1))
                    BulletPoint {
                        Text("Ahora la selección de edad de mascota es más precisa.",
                            color = SecondaryDarkest,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    Spacer(Modifier.height(MaterialTheme.dimens.extraSmall3))

                    BulletPoint {
                        Text("La edad se muestra correctamente en los detalles del perfil.",
                            color = SecondaryDarkest,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    Spacer(Modifier.height(MaterialTheme.dimens.extraSmall3))

                    BulletPoint {
                        Text("Al editar los datos, el requerimiento calórico se actualiza" +
                                " automáticamente según la nueva información.",
                            color = SecondaryDarkest,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    Spacer(Modifier.height(MaterialTheme.dimens.small1))
                    Box{
                        Text("Mejoras ✨",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            color = Primary,
                            modifier = Modifier.fillMaxWidth().blur(2.dp))
                        Text("Mejoras ✨",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            color = Primary,
                            modifier = Modifier.fillMaxWidth())
                    }
                    Spacer(Modifier.height(MaterialTheme.dimens.small1))
                    BulletPoint {
                        Text("Ahora, al asignar horarios para las comidas de tus mascotas," +
                                " la app te enviará notificaciones para que no se te pase ningún" +
                                " momento.\uD83D\uDCE3\n Una forma más cómoda de cuidar su rutina sin tener que" +
                                " recordarlo por tu cuenta.\uD83D\uDCC5 ✅",
                            color = SecondaryDarkest,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = {onDismiss()}) {
                    Box {
                        Text(text = "Aceptar",
                            color = SecondaryDarkest,
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.blur(3.dp).padding(3.dp))

                        Text(text = "Aceptar",
                            color = Primary,
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold,)
                    }
                }
            },
        )

}
