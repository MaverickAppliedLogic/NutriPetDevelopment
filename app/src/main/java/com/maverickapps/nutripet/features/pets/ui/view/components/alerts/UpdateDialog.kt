package com.maverickapps.nutripet.features.pets.ui.view.components.alerts

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.maverickapps.nutripet.features.pets.ui.view.components.alerts.updateDialogs.NeedToUpdateDialog
import com.maverickapps.nutripet.features.pets.ui.view.components.alerts.updateDialogs.UpdateNotesDialog

@Composable
fun UpdateDialog(
    modifier: Modifier = Modifier,
    needToUpdate: Boolean,
    showUpdateNotes: Boolean,
    onDismiss: () -> Unit = {}
    ){
    Box(modifier = modifier) {
        if (needToUpdate) {
            NeedToUpdateDialog(onDismiss = onDismiss)
        }
        else{
            if (showUpdateNotes) {
                UpdateNotesDialog(onDismiss = onDismiss, modifier = modifier)
            }
        }

    }
}