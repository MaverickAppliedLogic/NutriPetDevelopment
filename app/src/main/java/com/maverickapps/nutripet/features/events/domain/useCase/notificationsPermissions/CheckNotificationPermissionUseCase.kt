package com.maverickapps.nutripet.features.events.domain.useCase.notificationsPermissions

import com.maverickapps.nutripet.features.events.domain.notificationsEvents.permissionChecker.NotificationPermissionChecker
import javax.inject.Inject

class CheckNotificationPermissionUseCase @Inject constructor(
    private val notificationPermissionChecker: NotificationPermissionChecker
){
    operator fun invoke(): Boolean {
        return notificationPermissionChecker.isPostPermissionGranted()
    }
}