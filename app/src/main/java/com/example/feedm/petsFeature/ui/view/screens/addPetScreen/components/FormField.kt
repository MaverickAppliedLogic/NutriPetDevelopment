package com.example.feedm.petsFeature.ui.view.screens.addPetScreen.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import com.example.feedm.core.ui.theme.Error
import com.example.feedm.core.ui.theme.Good
import com.example.feedm.core.ui.theme.SecondaryDarkest
import com.example.feedm.petsFeature.ui.view.screens.addPetScreen.components.FormFieldStates.INVALID
import com.example.feedm.petsFeature.ui.view.screens.addPetScreen.components.FormFieldStates.VALID
import com.example.feedm.petsFeature.ui.view.screens.addPetScreen.components.FormFieldStates.WAITING

object FormFieldStates{
    const val WAITING = 0
    const val VALID = 1
    const val INVALID = 2
}

@Composable
fun FormField(
    label: String,
    modifier: Modifier = Modifier,
    state: Int ,
    expanded: Boolean = false,
    onTrailingIconClicked: () -> Unit,
    content: @Composable () -> Unit = {}
) {
    val expansion by animateDpAsState(
        targetValue = if (expanded) 100.dp else 0.dp
    )
    val iconRotation by animateFloatAsState(
        targetValue = if (expanded) 0f else -90f
    )

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(
                top = 10.dp, bottom = 10.dp,
                start = if (state == WAITING) 0.dp else 10.dp,
                end = 10.dp
            )
    ) {
        when (state) {
            WAITING ->
                RadioButton(selected = false, onClick = {},
                    colors = RadioButtonDefaults.colors(unselectedColor = SecondaryDarkest ))
            VALID ->
                Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null, tint = Good)
            INVALID ->
                Icon(imageVector = Icons.Default.Info, contentDescription = null, tint = Error)
        }
        Spacer(modifier = Modifier.padding(8.dp))
        Text(text = label, style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = { onTrailingIconClicked()}) {
            Icon(imageVector = Icons.Default.KeyboardArrowDown,
                modifier = Modifier.rotate(iconRotation),
                contentDescription = null)

        }

    }
    Row(modifier = Modifier.fillMaxWidth().height(expansion)) {
        content()

    }

    HorizontalDivider()
}