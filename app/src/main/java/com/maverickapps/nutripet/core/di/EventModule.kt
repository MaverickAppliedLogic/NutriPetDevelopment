package com.maverickapps.nutripet.core.di

import android.content.Context
import com.maverickapps.nutripet.features.events.contract.EventSetter
import com.maverickapps.nutripet.features.events.setter.MealNotificationSetter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object EventModule {

    @Provides
    fun provideMealNotificationSetter(
        @ApplicationContext context: Context): EventSetter{
        return MealNotificationSetter(context)
    }
}