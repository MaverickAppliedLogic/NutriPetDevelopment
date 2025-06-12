package com.maverickapps.tailyCare.petsFeature.ui.view.screens.registerPetScreen.components.contentComponents.fieldsComponents.formFields

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
import com.maverickapps.tailyCare.core.ui.theme.Error
import com.maverickapps.tailyCare.core.ui.theme.Good
import com.maverickapps.tailyCare.core.ui.theme.SecondaryDarkest
import com.maverickapps.tailyCare.core.ui.theme.dimens
import com.maverickapps.tailyCare.petsFeature.ui.view.screens.registerPetScreen.components.contentComponents.fieldsComponents.formFields.FormFieldStates.INVALID
import com.maverickapps.tailyCare.petsFeature.ui.view.screens.registerPetScreen.components.contentComponents.fieldsComponents.formFields.FormFieldStates.VALID
import com.maverickapps.tailyCare.petsFeature.ui.view.screens.registerPetScreen.components.contentComponents.fieldsComponents.formFields.FormFieldStates.WAITING

object FormFieldStates{
    const val WAITING = 0
    const val VALID = 1
    const val INVALID = 2
}

@Composable
fun FormField(
    modifier: Modifier = Modifier,
    label: String,
    isLastField: Boolean = false,
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
                top = MaterialTheme.dimens.extraSmall4,
                bottom = MaterialTheme.dimens.extraSmall4,
                start = if (state == WAITING) 0.dp else MaterialTheme.dimens.extraSmall4,
                end = MaterialTheme.dimens.extraSmall4
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
        Spacer(modifier = Modifier.padding(MaterialTheme.dimens.extraSmall3))
        Text(text = label, style = MaterialTheme.typography.bodyMedium)
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
    if (!isLastField) HorizontalDivider()
}