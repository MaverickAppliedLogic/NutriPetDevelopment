package com.maverickapps.nutripet.features.streak.contract

import com.maverickapps.nutripet.features.streak.domain.model.Streak

interface StreakRemoteRepository {
    suspend fun getStreak(userId: String): Streak
    suspend fun setStreak(streak: Streak)
}