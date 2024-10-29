package com.example.feedm.core.di

import com.example.feedm.data.cache.PetCacheProvider
import com.example.feedm.data.local.PetLocalStorageProvider
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
    fun providePetCache(): PetCacheProvider{
        return PetCacheProvider()
    }

    @Singleton
    @Provides
    fun providePetLocalStorage(petsFile: File) : PetLocalStorageProvider{
        return PetLocalStorageProvider(petsFile)
    }
}