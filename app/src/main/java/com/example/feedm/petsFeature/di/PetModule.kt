package com.example.feedm.petsFeature.di

import com.example.feedm.core.database.AppDatabase
import com.example.feedm.petsFeature.data.local.PetLocalStorageProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.io.File
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PetModule {

    @Singleton
    @Provides
    fun providePetDao(db: AppDatabase) = db.getPetDao()

    @Singleton
    @Provides
    fun providePetLocalStorage(petsFile: File) : PetLocalStorageProvider {
        return PetLocalStorageProvider(petsFile)
    }
}