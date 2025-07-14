package com.maverickapps.nutripet.features.pets.ui.view.screens.dashboardScreen.components.contentComponents

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.feedm.R
import com.maverickapps.nutripet.core.ui.theme.Neutral
import com.maverickapps.nutripet.core.ui.theme.NeutralLight
import com.maverickapps.nutripet.core.ui.theme.Primary
import com.maverickapps.nutripet.core.ui.theme.PrimaryDark

@Preview
@Composable
fun StreakCardInfo(
    modifier: Modifier = Modifier,
    streak: Int = 6
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Neutral),
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text("Completa los 14 dias para completar tu participación minima en la prueba" +
                    " cerrada y asi ser parte del grupo  con acceso anticipado a las futuras" +
                    " actualizaciones",
                textAlign = TextAlign.Center)
            Row {
                var rotation = 105f
                for (i in 0 until minOf(streak, 7)) {
                    Box {
                        Icon(
                            painterResource(R.mipmap.ic_petpaw),
                            contentDescription = "",
                            tint = PrimaryDark,
                            modifier = Modifier.rotate(rotation)
                        )
                        Icon(
                            painterResource(R.mipmap.ic_petpaw),
                            contentDescription = "",
                            tint = Primary,
                            modifier = Modifier
                                .rotate(rotation)
                                .blur(3.dp)
                        )
                    }
                    rotation = if (rotation == 105f) 75f else 105f
                }

                for (i in 0 until 7 - streak) {
                    Icon(
                        painterResource(R.mipmap.ic_petpaw),
                        contentDescription = "",
                        tint = NeutralLight,
                        modifier = Modifier.rotate(rotation)
                    )
                    rotation = if (rotation == 105f) 75f else 105f
                }
            }
            Row {
                var rotation = 75f
                for (i in 0 until minOf(streak - 7, 7)) {
                    Box {
                        Icon(
                            painterResource(R.mipmap.ic_petpaw),
                            contentDescription = "",
                            tint = PrimaryDark,
                            modifier = Modifier.rotate(rotation)
                        )
                        Icon(
                            painterResource(R.mipmap.ic_petpaw),
                            contentDescription = "",
                            tint = Primary,
                            modifier = Modifier
                                .rotate(rotation)
                                .blur(3.dp)
                        )
                    }
                    rotation = if (rotation == 105f) 75f else 105f
                }

                for (i in 0 until minOf(14 - streak, 7)) {
                    Icon(
                        painterResource(R.mipmap.ic_petpaw),
                        contentDescription = "",
                        tint = NeutralLight,
                        modifier = Modifier.rotate(rotation)
                    )
                    rotation = if (rotation == 105f) 75f else 105f
                }
            }
        }
    }
}