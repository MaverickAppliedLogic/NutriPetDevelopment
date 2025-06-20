package com.maverickapps.nutripet.petsFeature.ui.view.screens.registerPetScreen.components.contentComponents.scaffolds

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun LandscapeScaffold(
    content: @Composable (PaddingValues) -> Unit = {}
) {
    Scaffold(
        modifier = Modifier,
        contentWindowInsets = WindowInsets.safeDrawing
    ) { contentPadding -> content(contentPadding) }
}