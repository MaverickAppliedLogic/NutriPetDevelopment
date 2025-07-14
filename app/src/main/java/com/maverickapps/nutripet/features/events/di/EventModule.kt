package com.maverickapps.nutripet.features.events.di

import android.app.AlarmManager
import android.content.Context
import com.maverickapps.nutripet.features.events.domain.dayChangerEvents.scheduler.DayChangerScheduler
import com.maverickapps.nutripet.features.events.domain.notificationsEvents.permissionChecker.NotificationPermissionChecker
import com.maverickapps.nutripet.features.events.domain.notificationsEvents.scheduler.NotificationScheduler
import com.maverickapps.nutripet.features.events.permissionChecker.ExactAlarmPermissionChecker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object EventModule {

    @Provides
    fun provideAlarmManager(@ApplicationContext context: Context): AlarmManager {
        val alarmManager: AlarmManager =
            context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        return alarmManager
    }

    @Provides
    fun provideNotificationScheduler(
        @ApplicationContext context: Context,
        alarmManager: AlarmManager
    ): NotificationScheduler {
        return NotificationScheduler(context,alarmManager)
    }

    @Provides
    fun provideDayChangerScheduler(
        @ApplicationContext context: Context,
        alarmManager: AlarmManager): DayChangerScheduler {
        return DayChangerScheduler(context, alarmManager)
    }

    @Provides
    fun provideNotificationPermissionChecker(
        @ApplicationContext context: Context
    ): NotificationPermissionChecker {
        return NotificationPermissionChecker(context)
    }

    @Provides
    fun provideExactAlarmPermissionChecker(
        @ApplicationContext context: Context,
        alarmManager: AlarmManager
    ): ExactAlarmPermissionChecker {
        return ExactAlarmPermissionChecker(context, alarmManager)
    }
}