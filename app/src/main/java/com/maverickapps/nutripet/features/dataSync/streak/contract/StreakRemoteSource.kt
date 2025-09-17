package com.maverickapps.nutripet.features.dataSync.streak.contract

import com.maverickapps.nutripet.features.streak.domain.model.Streak

interface StreakRemoteSource {
    suspend fun getStreak(userId: String): Streak
    suspend fun setStreak(streak: Streak)
}