package com.maverickapps.nutripet.features.pets.ui.view.screens.registerPetScreen.components.contentComponents.scaffolds

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.maverickapps.nutripet.core.ui.theme.NeutralLight
import com.maverickapps.nutripet.core.ui.theme.dimens
import com.maverickapps.nutripet.features.pets.domain.objectTasks.pet.model.PetModel
import com.maverickapps.nutripet.features.pets.ui.view.screens.registerPetScreen.utils.FormStateManager

@Composable
fun PortraitScaffold(
    formStateManager: FormStateManager,
    petToBeAdded: PetModel,
    navigateBack: (Boolean) -> Unit = {},
    content: @Composable (PaddingValues) -> Unit = {}
) {
    Scaffold(
        bottomBar = {
            BottomAppBar(containerColor = NeutralLight) {
                Row(
                    Modifier
                        .fillMaxSize()
                        .background(Color.Transparent),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        shape = RoundedCornerShape(MaterialTheme.dimens.extraSmall2),
                        modifier = Modifier
                            .width(MaterialTheme.dimens.extraLarge2)
                            .height(MaterialTheme.dimens.medium3),
                        onClick = {
                            formStateManager.actionTriggered(
                                0,
                                0,
                                petToBeAdded,
                                navigateBack
                            )
                        },
                    ) {
                        Text(text = "Confirmar")
                    }
                }
            }
        },
        contentWindowInsets = WindowInsets.safeDrawing
    ) { contentPadding -> content(contentPadding) }
}