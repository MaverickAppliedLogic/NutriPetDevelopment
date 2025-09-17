package com.maverickapps.nutripet.core.domain.useCase.appVersion

import com.maverickapps.nutripet.core.data.uniqueRepositories.VersionManageRepository
import javax.inject.Inject

class CheckCurrentVerIsLatestUseCase @Inject constructor(
    private val versionManageRepository: VersionManageRepository,
    private val appVersion: Double
) {
    suspend operator fun invoke(): Pair<Boolean,Boolean> {
        val latestVersion = versionManageRepository.getLatestVersion()
        println("latestVersion: $latestVersion")
        val currentVersion = versionManageRepository.getVersionLocalStorage()
        println("currentVersion: $currentVersion")
        val isRecentlyUpdated = latestVersion > currentVersion
        println("isRecentlyUpdated: $isRecentlyUpdated")
        val needToUpdate = latestVersion > appVersion
        println("needToUpdate: $needToUpdate")
        return Pair(needToUpdate, isRecentlyUpdated)
    }
}