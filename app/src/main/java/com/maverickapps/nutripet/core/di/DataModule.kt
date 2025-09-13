package com.maverickapps.nutripet.core.di

import android.content.Context
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import com.maverickapps.nutripet.core.data.datastore.StreakDatastore
import com.maverickapps.nutripet.core.data.datastore.VersionDatastore
import com.maverickapps.nutripet.core.data.room.AppDatabase
import com.maverickapps.nutripet.core.data.room.MIGRATION
import com.maverickapps.nutripet.core.data.room.dao.FoodDao
import com.maverickapps.nutripet.core.data.room.dao.MealDao
import com.maverickapps.nutripet.core.data.room.dao.PetDao
import com.maverickapps.nutripet.core.data.room.dao.PetFoodDao
import com.maverickapps.nutripet.features.dataSync.streak.contract.StreakLocalSource
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
object DataModule {

    private const val USER_PREFERENCES_NAME = "user_preferences"

    @Singleton
    @Provides
    fun provideDataStore(@ApplicationContext context: Context) = PreferenceDataStoreFactory.create{
        context.preferencesDataStoreFile(USER_PREFERENCES_NAME)
    }


    @Singleton
    @Provides
    @Named("UpdateFile")
    fun provideVersionFilesDir(@ApplicationContext context: Context): File {
        val updateFile = File(context.filesDir, "updateProvisionalState")
        if (!updateFile.exists()) {
            updateFile.createNewFile()
        }
        return updateFile
    }

    @Singleton
    @Provides
    fun provideVersionDatastore(@Named("UpdateFile")file: File): VersionDatastore {
        return VersionDatastore(file)
    }

    @Singleton
    @Named("StreakFile")
    @Provides
    fun provideStreakFilesDir(@ApplicationContext context: Context): File {
        val streakFile = File(context.filesDir, "streakFile")
        if (!streakFile.exists()) {
            streakFile.createNewFile()
        }
        return streakFile
    }

    @Singleton
    @Provides
    fun provideStreakDatastore(@Named("StreakFile")file: File): StreakLocalSource {
        return StreakDatastore(file)
    }

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, "app_database")
            .addMigrations(MIGRATION)
            .build()

    @Provides
    fun providePetDao(db: AppDatabase): PetDao {
        return db.getPetDao()
    }

    @Provides
    fun provideMealDao(db: AppDatabase): MealDao {
        return db.getMealDao()
    }

    @Provides
    fun provideFoodDao(db: AppDatabase): FoodDao {
        return db.getFoodDao()
    }

    @Provides
    fun providePetFoodDao(db: AppDatabase): PetFoodDao {
        return db.getPetFoodDao()
    }

}

