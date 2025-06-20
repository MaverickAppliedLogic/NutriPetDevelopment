package com.maverickapps.nutripet.core.domain.useCase

import com.maverickapps.nutripet.core.data.VersionManageRepository
import javax.inject.Inject

class CheckCurrentVerIsLatestUseCase @Inject constructor(
    private val versionManageRepository: VersionManageRepository
) {
    suspend operator fun invoke(): Boolean {
        val latestVersion = versionManageRepository.getLatestVersion()
        val currentVersion = versionManageRepository.getVersionLocalStorage()
        return latestVersion > currentVersion
    }
}