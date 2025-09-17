package com.maverickapps.nutripet.features.dataSync.streak.di

import com.maverickapps.nutripet.core.data.firestore.streak.FirestoreStreakSource
import com.maverickapps.nutripet.features.dataSync.streak.contract.StreakRemoteSource
import com.maverickapps.nutripet.features.dataSync.streak.data.StreakRepository
import com.maverickapps.nutripet.features.dataSync.streak.domain.SyncStreakUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StreakModule {

    @Singleton
    @Provides
    fun provideStreakRemoteDataSource(): StreakRemoteSource {
        return FirestoreStreakSource()
    }

    @Singleton
    @Provides
    fun provideSyncStreakUseCase(streakRepository: StreakRepository): SyncStreakUseCase {
        return SyncStreakUseCase(streakRepository)
    }

}