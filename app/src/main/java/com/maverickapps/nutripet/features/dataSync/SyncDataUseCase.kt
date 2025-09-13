package com.maverickapps.nutripet.features.dataSync

import com.maverickapps.nutripet.features.dataSync.streak.domain.SyncStreakUseCase
import javax.inject.Inject

class SyncDataUseCase @Inject constructor(
    private val syncStreakUseCase: SyncStreakUseCase
) {
    suspend operator fun invoke(userId: String){
        syncStreakUseCase(userId)
    }
}