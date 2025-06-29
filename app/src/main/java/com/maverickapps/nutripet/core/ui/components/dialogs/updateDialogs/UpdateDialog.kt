package com.maverickapps.nutripet.core.ui.components.dialogs.updateDialogs

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

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