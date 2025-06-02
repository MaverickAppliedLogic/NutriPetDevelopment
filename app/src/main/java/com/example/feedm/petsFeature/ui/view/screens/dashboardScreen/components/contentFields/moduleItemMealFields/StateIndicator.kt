package com.example.feedm.petsFeature.ui.view.screens.dashboardScreen.components.contentFields.moduleItemMealFields

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.twotone.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.feedm.core.ui.theme.Error
import com.example.feedm.core.ui.theme.Good
import com.example.feedm.core.ui.theme.Pending
import com.example.feedm.core.ui.theme.SecondaryDarkest

@Composable
fun StateIndicator(
    modifier: Modifier = Modifier,
    state: Int,
    onClick: () -> Unit = {}
) {
    Row(
        modifier.fillMaxHeight(), verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        when (state) {
            0 -> {
                Icon(
                    imageVector = Icons.Default.Check, contentDescription = "",
                    tint = Good, modifier = Modifier.size(25.dp)
                )
            }

            1 -> {
                Icon(
                    imageVector = Icons.TwoTone.Warning, contentDescription = "",
                    tint = Pending, modifier = Modifier.weight(0.5f)
                )
                IconButton(onClick = { onClick() }, modifier = Modifier.weight(0.5f)) {
                    Icon(
                        imageVector = Icons.Default.Add, contentDescription = "",
                        tint = SecondaryDarkest
                    )
                }
            }

            2 -> {
                Icon(
                    imageVector = Icons.Default.Info, contentDescription = "",
                    tint = Error, modifier = Modifier.weight(0.5f)
                )
                IconButton(onClick = {}, modifier = Modifier.weight(0.5f)) {
                    Icon(
                        imageVector = Icons.Default.Add, contentDescription = "",
                        tint = Error
                    )

                }

            }

        }
    }
}