package com.maverickapps.nutripet.features.events.domain.useCase.notificationsPermissions

import android.app.Activity
import android.os.Build
import androidx.annotation.RequiresApi
import com.maverickapps.nutripet.features.events.domain.notificationsEvents.permissionChecker.NotificationPermissionChecker
import javax.inject.Inject

class RequesNotificationPermissionUseCase @Inject constructor(
    private val notificationPermissionChecker: NotificationPermissionChecker
) {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    operator fun invoke(activity: Activity) {
        notificationPermissionChecker.requestPostPermission(activity)
    }
}