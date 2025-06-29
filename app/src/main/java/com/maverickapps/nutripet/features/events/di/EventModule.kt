package com.maverickapps.nutripet.features.events.di

import android.content.Context
import com.maverickapps.nutripet.features.events.scheduler.NotificationScheduler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object EventModule {

    @Provides
    fun provideNotificationScheduler(
        @ApplicationContext context: Context): NotificationScheduler {
        return NotificationScheduler(context)
    }
}