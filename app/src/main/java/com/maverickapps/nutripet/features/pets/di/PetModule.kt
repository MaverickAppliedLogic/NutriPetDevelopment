package com.maverickapps.nutripet.features.pets.di

import android.content.Context
import com.maverickapps.nutripet.features.pets.data.datastore.PetDatastore
import com.maverickapps.nutripet.features.pets.utils.caloriesCalculator.implementations.CaloriesCalculatorCat
import com.maverickapps.nutripet.features.pets.utils.caloriesCalculator.implementations.CaloriesCalculatorDog
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

    @Singleton
    @Provides
    @Named("PetsFile")
    fun providePetsFilesDir(@ApplicationContext context: Context): File {
        val petsFile = File(context.filesDir, "pets")
        if (!petsFile.exists()) {
            petsFile.createNewFile()
        }
        return petsFile
    }

    @Singleton
    @Provides
    fun providePetDatastore(@Named("PetsFile")file: File): PetDatastore {
        return PetDatastore(file)
    }
}