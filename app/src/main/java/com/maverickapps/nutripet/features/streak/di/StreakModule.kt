package com.maverickapps.nutripet.features.streak.di

import com.maverickapps.nutripet.core.data.firestore.streak.FirestoreStreakDataSource
import com.maverickapps.nutripet.features.streak.contract.StreakRemoteDataSource
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
    fun provideStreakRemoteDataSource(): StreakRemoteDataSource {
        return FirestoreStreakDataSource()
    }
}