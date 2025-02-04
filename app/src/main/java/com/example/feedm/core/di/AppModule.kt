package com.example.feedm.core.di

import android.content.Context
import com.example.feedm.core.domain.model.PetModel
import com.example.feedm.core.utils.CaloriesCalculator
import com.example.feedm.core.utils.CaloriesCalculatorCat
import com.example.feedm.core.utils.CaloriesCalculatorDog
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideContext(@ApplicationContext context: Context):Context{
        return context
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