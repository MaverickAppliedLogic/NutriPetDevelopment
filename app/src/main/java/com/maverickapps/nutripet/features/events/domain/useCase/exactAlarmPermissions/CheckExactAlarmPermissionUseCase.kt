package com.maverickapps.nutripet.features.events.domain.useCase.exactAlarmPermissions

import com.maverickapps.nutripet.features.events.permissionChecker.ExactAlarmPermissionChecker
import javax.inject.Inject

class CheckExactAlarmPermissionUseCase @Inject constructor(
    private val exactAlarmPermissionChecker: ExactAlarmPermissionChecker
) {
    operator fun invoke(): Boolean {
        return exactAlarmPermissionChecker.isSchedulePermissionGranted()
    }
}