package com.example.feedm.petsFeature.ui.view.screens.addPetScreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.feedm.petsFeature.ui.view.components.ScrollableSelector
import com.example.feedm.petsFeature.ui.view.screens.addPetScreen.formItemsHandler

@Composable
fun WeightField(
    expansionState: Boolean,
    fieldState: Int,
    modifier: Modifier
) {
    FormField(
        label = "Peso",
        state = fieldState,
        expanded = expansionState,
        onTrailingIconClicked = { formItemsHandler.onItemExpansionChanged(3) },
        modifier = modifier
    ) {
        Row( horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(bottom = 35.dp)) {
            ScrollableSelector(
                items = (1..100).toList().map { it.toString() })
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = "Kg", textAlign = TextAlign.Center)
        }
    }
}