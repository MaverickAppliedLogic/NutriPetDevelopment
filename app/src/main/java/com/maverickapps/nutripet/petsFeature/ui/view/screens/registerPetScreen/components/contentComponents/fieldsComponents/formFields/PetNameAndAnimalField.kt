package com.maverickapps.nutripet.petsFeature.ui.view.screens.registerPetScreen.components.contentComponents.fieldsComponents.formFields

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.snap
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.feedm.R
import com.maverickapps.nutripet.core.ui.theme.Error
import com.maverickapps.nutripet.core.ui.theme.Primary
import com.maverickapps.nutripet.core.ui.theme.PrimaryDark
import com.maverickapps.nutripet.core.ui.theme.PrimaryLightest
import com.maverickapps.nutripet.core.ui.theme.SecondaryDarkest
import com.maverickapps.nutripet.core.ui.theme.dimens

@Composable
fun PetNameAndAnimalField(
    animal: String,
    name: String,
    expansionState: Boolean,
    fieldState: Int,
    modifier: Modifier,
    onTrailingIconClicked: () -> Unit = {},
    onNameChanged: (String) -> Unit = {},
    onAnimalChanged: (String) -> Unit = {}
) {
    FormField(
        label = "Nuevo Compañero",
        state = fieldState,
        expanded = expansionState,
        onTrailingIconClicked = { onTrailingIconClicked()},
        modifier = modifier
    ) {

        /**Executes a rotation animation for the `Icons.Default.Refresh`
         * whenever the card is clicked.
         * ```
         * When the user clicks the PetCard, the animation begins by updating the 'animal' value.
         * The 'animalChanged' flag is then set to true, disabling the card to prevent further
         * clicks and ensuring the animation isn´t paused in the middle of the rotation.
         * ```
         ***/

        var hasAnimalChanged by remember { mutableStateOf(false) }
        var currentAnimal by remember { mutableStateOf(animal) }
        val rotationAnim by animateFloatAsState(
            targetValue = if (hasAnimalChanged) 360f else 0f,
            label = "",
            animationSpec = if (hasAnimalChanged) tween(500) else snap(0),
            finishedListener = { hasAnimalChanged = false }
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.dimens.small1)
        ) {
            Spacer(modifier = Modifier.weight(0.1f, true))
            Card(
                elevation = CardDefaults.elevatedCardElevation(MaterialTheme.dimens.extraSmall1),
                enabled = !hasAnimalChanged,
                colors = CardDefaults.cardColors(
                    containerColor = Primary,
                    contentColor = Color.Transparent,
                    disabledContainerColor = Primary,
                    disabledContentColor = Color.Transparent
                ),
                onClick = {
                    currentAnimal = if (currentAnimal == "dog") "cat" else "dog"
                    hasAnimalChanged = true
                    onAnimalChanged(currentAnimal)
                },
                modifier = Modifier
                    .padding(bottom = MaterialTheme.dimens.small1)
                    .weight(0.3f)
                    .align(Alignment.CenterVertically)
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Change animal",
                    tint = Color.Black,
                    modifier = Modifier
                        .scale(0.8f)
                        .align(Alignment.CenterHorizontally)
                        .rotate(rotationAnim)
                )
                val animalImage = if (currentAnimal == "dog") {
                    R.drawable.img_dog_illustration
                } else {
                    R.drawable.icono_gato_sinfondo
                }
                Image(
                    painter = painterResource(id = animalImage),
                    contentDescription = "",
                    modifier = Modifier
                        .clip(RoundedCornerShape(MaterialTheme.dimens.extraSmall4))
                        .fillMaxSize()
                        .padding(bottom = MaterialTheme.dimens.extraSmall2)
                )
            }
            Spacer(modifier = Modifier.weight(0.1f))
            AnimatedVisibility(
                visible = expansionState,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically(),
                modifier = Modifier
                    .weight(0.5f)
                    .align(Alignment.CenterVertically)
            ) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { onNameChanged(it) },
                    label = { Text(text = stringResource(id = R.string.fa_hintEtPetName)) },
                    isError = false,
                    colors = TextFieldDefaults.colors(
                        cursorColor = SecondaryDarkest,
                        unfocusedContainerColor = PrimaryLightest,
                        focusedContainerColor = PrimaryLightest,
                        errorContainerColor = Error,
                        unfocusedLabelColor = PrimaryDark,
                        focusedLabelColor = Primary,
                        errorLabelColor = Error,
                        unfocusedTextColor = PrimaryDark,
                        focusedTextColor = SecondaryDarkest,
                        errorTextColor = Error,
                        focusedIndicatorColor = Primary,
                        unfocusedIndicatorColor = PrimaryDark,
                        errorIndicatorColor = Error
                    ),
                    modifier = Modifier.padding(bottom = MaterialTheme.dimens.small1)
                )
            }
            Spacer(modifier = Modifier.weight(0.1f, true))
        }
    }
}