package com.maverickapps.nutripet.features.dataSync.streak.contract

import com.maverickapps.nutripet.features.streak.domain.model.Streak

interface StreakLocalSource {
    fun getStreak(): Streak
    fun setStreak(streak: Streak)
}