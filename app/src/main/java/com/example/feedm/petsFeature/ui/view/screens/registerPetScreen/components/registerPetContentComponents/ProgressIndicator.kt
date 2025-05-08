package com.example.feedm.petsFeature.ui.view.screens.registerPetScreen.components.registerPetContentComponents

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.feedm.R
import com.example.feedm.core.ui.theme.Neutral
import com.example.feedm.core.ui.theme.NeutralDark
import com.example.feedm.core.ui.theme.NeutralLight
import com.example.feedm.core.ui.theme.Primary
import com.example.feedm.petsFeature.ui.view.screens.registerPetScreen.components.registerPetContentComponents.formFields.FormFieldStates.VALID
import com.example.feedm.petsFeature.ui.view.screens.registerPetScreen.utils.FormItemsInteractionsHandler.Companion.ACTIVITY_FIELD
import com.example.feedm.petsFeature.ui.view.screens.registerPetScreen.utils.FormItemsInteractionsHandler.Companion.AGE_FIELD
import com.example.feedm.petsFeature.ui.view.screens.registerPetScreen.utils.FormItemsInteractionsHandler.Companion.GOAL_FIELD
import com.example.feedm.petsFeature.ui.view.screens.registerPetScreen.utils.FormItemsInteractionsHandler.Companion.PET_NAME_FIELD
import com.example.feedm.petsFeature.ui.view.screens.registerPetScreen.utils.FormItemsInteractionsHandler.Companion.WEIGHT_FIELD
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProgressIndicator(
    validateList: List<Int>,
    modifier: Modifier = Modifier
) {

    val progress by animateFloatAsState(
        targetValue = validateList.count { it == VALID }.toFloat() / 10,
        animationSpec = tween(500),
        label = ""
    )
    val sliderText = String.format(Locale.getDefault(), "%.0f/7", progress * 10)
    val indicatorText =
        if (progress == 0.7f) "¡Datos al completo! :D"
        else if (progress < 0.5f || validateList[PET_NAME_FIELD] != VALID ||
            validateList[AGE_FIELD] != VALID || validateList[WEIGHT_FIELD] != VALID ||
            validateList[GOAL_FIELD] != VALID || validateList[ACTIVITY_FIELD] != VALID
        ) {
            "Hacen falta más datos"
        } else "Datos suficientes :)"
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(NeutralDark, Neutral)
                )
            )
    ) {
        Column {
            Text(
                text = "Nuevo Compañero",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                color = Primary,
                modifier = Modifier.padding(10.dp)
            )
            Spacer(modifier = Modifier.weight(1f, true))
            Text(
                text = "$sliderText $indicatorText",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                color = Primary,
                modifier = Modifier.padding(horizontal = 15.dp)
            )
            Slider(
                value = progress,
                onValueChange = { },
                colors = SliderDefaults.colors(
                    thumbColor = Primary,
                    activeTrackColor = Primary,
                    inactiveTrackColor = NeutralLight,
                    activeTickColor = Primary,
                    inactiveTickColor = NeutralLight
                ),
                thumb = {
                    Icon(
                        painter = painterResource(R.mipmap.dog_icon),
                        contentDescription = null,
                        tint = Primary,
                        modifier = Modifier
                            .size(ButtonDefaults.IconSize)
                            .scale(1.5f)
                    )
                },
                valueRange = 0f..0.7f,
                modifier = Modifier.padding(horizontal = 10.dp)
            )
            Spacer(modifier = Modifier.weight(1f, true))
        }

    }
}