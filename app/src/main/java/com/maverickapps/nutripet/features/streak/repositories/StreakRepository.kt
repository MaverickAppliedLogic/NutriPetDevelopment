package com.maverickapps.nutripet.features.streak.repositories

import com.maverickapps.nutripet.features.streak.contract.StreakRemoteRepository
import com.maverickapps.nutripet.features.streak.domain.model.Streak
import javax.inject.Inject

class StreakRepository @Inject constructor(
    private val remoteDataSource: StreakRemoteRepository,
)
{
    suspend fun setStreak(streak: Streak){
        remoteDataSource.setStreak(streak)
    }

    suspend fun getStreak(userId: String): Streak{
       return remoteDataSource.getStreak(userId)
    }
}