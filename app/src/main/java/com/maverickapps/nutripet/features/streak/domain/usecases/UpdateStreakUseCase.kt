package com.maverickapps.nutripet.features.streak.domain.usecases

import com.maverickapps.nutripet.features.dataSync.streak.data.StreakRepository
import com.maverickapps.nutripet.features.streak.domain.model.Streak
import javax.inject.Inject

class UpdateStreakUseCase @Inject constructor(
    private val streakRepository: StreakRepository
) {
    operator fun invoke(streak: Streak) =
        streakRepository.setLocalStreak(streak)
}