package com.maverickapps.tailyCare.core.di

import android.content.Context
import com.maverickapps.tailyCare.petsFeature.utils.CaloriesCalculatorCat
import com.maverickapps.tailyCare.petsFeature.utils.CaloriesCalculatorDog
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import android.icu.util.Calendar
import java.util.Locale
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    //Classes
    @Singleton
    @Provides
    fun provideContext(@ApplicationContext context: Context):Context{
        return context
    }

    @Singleton
    @Provides
    fun provideCalendar(@ApplicationContext context: Context): Calendar {
            return Calendar.getInstance(Locale.getDefault())
    }

    //Utils
    @Singleton
    @Provides
    fun provideCalculatorDog(): CaloriesCalculatorDog {
        return CaloriesCalculatorDog()
    }

    @Singleton
    @Provides
    fun provideCalculatorCat(): CaloriesCalculatorCat {
        return CaloriesCalculatorCat()
    }



}