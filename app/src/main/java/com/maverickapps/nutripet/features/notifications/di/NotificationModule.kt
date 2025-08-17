package com.maverickapps.nutripet.features.notifications.di

import android.content.Context
import com.maverickapps.nutripet.features.notifications.data.datastore.NotificationDatastore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.io.File
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NotificationModule {

    @Singleton
    @Provides
    @Named("NotificationsFile")
    fun provideNotificationFilesDir(@ApplicationContext context: Context): File {
        val notificationsFile = File(context.filesDir, "notifications")
        if (!notificationsFile.exists()) {
            notificationsFile.createNewFile()
        }
        return notificationsFile
    }

    @Singleton
    @Provides
    fun provideNotificationDatastore(@Named("NotificationsFile")file: File): NotificationDatastore {
        return NotificationDatastore(file)
    }

}