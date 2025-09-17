package com.maverickapps.nutripet.features.streak.domain.usecases

import com.maverickapps.nutripet.features.dataSync.streak.data.StreakRepository
import javax.inject.Inject

class GetStreakUseCase @Inject constructor(
    private val streakRepository: StreakRepository
){
   operator fun invoke() = streakRepository.getLocalStreak()
}