package com.maverickapps.nutripet.core.domain.useCase.appVersion

import com.maverickapps.nutripet.core.data.uniqueRepositories.VersionManageRepository
import javax.inject.Inject

class FetchCurrentVerUseCase @Inject constructor(
    private val versionManageRepository: VersionManageRepository
) {
    suspend operator fun invoke() {
        versionManageRepository.fetchVersionLocalStorage()
    }
}