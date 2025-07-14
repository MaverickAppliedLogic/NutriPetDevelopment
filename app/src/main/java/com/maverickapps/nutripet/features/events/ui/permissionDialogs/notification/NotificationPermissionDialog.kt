package com.maverickapps.nutripet.features.events.ui.permissionDialogs.notification

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun NotificationPermissionDialog(
    postPermissionGranted: Boolean,
    schedulePermissionGranted: Boolean,
    postPermissionRequest: () -> Unit,
    schedulePermissionRequest: () -> Unit,
    dismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ){
        if (!postPermissionGranted) {
            PostPermissionDialog(
                postPermissionRequest = postPermissionRequest,
                postPermissionDismiss = { dismiss() },
                modifier = modifier.padding(horizontal = 16.dp)
            )
        } else if (!schedulePermissionGranted) {
            SchedulePermissionDialog(
                schedulePermissionRequest = schedulePermissionRequest,
                schedulePermissionDismiss = { dismiss() },
                modifier = modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@Preview(showBackground = true, heightDp = 600, widthDp = 300)
@Composable
fun NotificationPermissionDialogPreview() {
        NotificationPermissionDialog(
            postPermissionGranted = true,
            schedulePermissionGranted = false,
            postPermissionRequest = {},
            schedulePermissionRequest = {},
            dismiss = {},
            modifier = Modifier.fillMaxHeight(0.7f)
        )
}