package com.maverickapps.nutripet.features.dataSync.streak.data

import com.maverickapps.nutripet.features.dataSync.streak.contract.StreakLocalSource
import com.maverickapps.nutripet.features.dataSync.streak.contract.StreakRemoteSource
import com.maverickapps.nutripet.features.streak.domain.model.Streak
import javax.inject.Inject

class StreakRepository @Inject constructor(
    private val remoteDataSource: StreakRemoteSource,
    private val localDataSource: StreakLocalSource
)
{
    fun setLocalStreak(streak: Streak){
        localDataSource.setStreak(streak)
    }

    fun getLocalStreak(): Streak{
        return localDataSource.getStreak()
    }

    suspend fun setRemoteStreak(streak: Streak){
        remoteDataSource.setStreak(streak)
    }

    suspend fun getRemoteStreak(userId: String): Streak{
       return remoteDataSource.getStreak(userId)
    }
}