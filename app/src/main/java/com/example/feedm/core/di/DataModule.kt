package com.example.feedm.core.di

import android.content.Context
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideFilesDir (@ApplicationContext context: Context): File{
        val petsFile = File(context.filesDir, "pets")
        if(!petsFile.exists()){
            petsFile.createNewFile()
        }
        return petsFile
    }
}