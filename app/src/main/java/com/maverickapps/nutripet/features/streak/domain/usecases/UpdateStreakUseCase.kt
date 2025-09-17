package com.maverickapps.nutripet.features.streak.domain.usecases

import com.maverickapps.nutripet.features.dataSync.streak.data.StreakRepository
import com.maverickapps.nutripet.features.dataSync.streak.domain.SyncStreakUseCase
import com.maverickapps.nutripet.features.streak.domain.model.Streak
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class UpdateStreakUseCase @Inject constructor(
    private val streakRepository: StreakRepository,
    private val syncStreakUseCase: SyncStreakUseCase
) {
   operator fun invoke(streak: Streak) {
        streakRepository.setLocalStreak(streak)
       println("User: $streak")
       CoroutineScope(Dispatchers.IO).launch {
           if (streak.userId!=""){
               println("Sync streak called")
               syncStreakUseCase(streak.userId)
           }
       }
    }
}