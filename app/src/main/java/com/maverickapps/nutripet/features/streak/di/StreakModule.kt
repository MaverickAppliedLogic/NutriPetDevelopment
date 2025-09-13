package com.maverickapps.nutripet.features.streak.di

import com.maverickapps.nutripet.core.data.firestore.streak.FirestoreStreakRepository
import com.maverickapps.nutripet.features.streak.contract.StreakRemoteRepository
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
    fun provideStreakRemoteDataSource(): StreakRemoteRepository {
        return FirestoreStreakRepository()
    }
}