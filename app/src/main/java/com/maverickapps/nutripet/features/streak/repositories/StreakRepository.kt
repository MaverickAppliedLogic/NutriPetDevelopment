package com.maverickapps.nutripet.features.streak.repositories

import com.maverickapps.nutripet.features.streak.contract.StreakRemoteDataSource
import javax.inject.Inject

class StreakRepository @Inject constructor(
    private val remoteDataSource: StreakRemoteDataSource,
)
{
}