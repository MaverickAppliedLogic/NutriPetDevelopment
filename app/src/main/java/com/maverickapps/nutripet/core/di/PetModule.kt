package com.maverickapps.nutripet.core.di

import com.maverickapps.nutripet.features.pets.utils.caloriesCalculator.implementations.CaloriesCalculatorCat
import com.maverickapps.nutripet.features.pets.utils.caloriesCalculator.implementations.CaloriesCalculatorDog
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PetModule {

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