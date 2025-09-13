package com.maverickapps.nutripet.features.dataSync.streak.domain

import com.maverickapps.nutripet.features.dataSync.streak.data.StreakRepository
import javax.inject.Inject

class SyncStreakUseCase @Inject constructor(
    private val streakRepository: StreakRepository,
) {
    suspend operator fun invoke(userId: String) {
        val localStreak = streakRepository.getLocalStreak()
        val remoteStreak = streakRepository.getRemoteStreak(userId)

        if (localStreak.lastDate > remoteStreak.lastDate){
            streakRepository.setRemoteStreak(localStreak.copy(userId = userId))
            println("Remote Streak fetched")
        }
        else if (localStreak.lastDate < remoteStreak.lastDate){
            streakRepository.setLocalStreak(remoteStreak.copy(userId = userId))
            println("Local Streak fetched")
        }
        else{
            println("Streak is up to date")
        }
    }
}