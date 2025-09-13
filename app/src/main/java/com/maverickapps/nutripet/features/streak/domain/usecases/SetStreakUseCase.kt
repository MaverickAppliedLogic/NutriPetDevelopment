package com.maverickapps.nutripet.features.streak.domain.usecases

import com.maverickapps.nutripet.features.streak.domain.model.Streak
import com.maverickapps.nutripet.features.streak.repositories.StreakRepository
import javax.inject.Inject

class SetStreakUseCase @Inject constructor(
    private val streakRepository: StreakRepository,
){
    suspend operator fun invoke(streak: Streak)=
        streakRepository.setStreak(streak)
}