package com.maverickapps.nutripet.features.streak.contract

import com.maverickapps.nutripet.features.streak.domain.model.Streak

interface StreakRemoteDataSource {
    suspend fun getStreak(userId: String): Streak
    suspend fun setStreak(userId: String, streak: Streak)
}