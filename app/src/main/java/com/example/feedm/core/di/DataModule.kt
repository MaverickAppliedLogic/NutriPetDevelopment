package com.example.feedm.core.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.feedm.data.database.PetDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.io.File
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

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, PetDatabase::class.java, "pet_database")
            .addMigrations(MIGRATION2_3)
            .build()

    @Singleton
    @Provides
    fun providePetDao(db:PetDatabase) = db.getPetDao()

    val MIGRATION2_3 = object: Migration(2,3){
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("""
            CREATE TABLE pet_table_new (
                id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 
                animal TEXT NOT NULL, 
                name TEXT NOT NULL, 
                age TEXT NOT NULL, 
                weight REAL NOT NULL, 
                genre TEXT NOT NULL, 
                esterilized TEXT NOT NULL, 
                activity TEXT NOT NULL, 
                goal TEXT NOT NULL, 
                allergies TEXT NOT NULL DEFAULT 'Nada', 
                query TEXT NOT NULL
            )
        """.trimIndent())

                db.execSQL("""
            INSERT INTO pet_table_new (animal, name, age, weight, genre, esterilized, activity, goal, allergies, query)
            SELECT animal, name, age, weight, genre, esterilized, activity, goal, allergies, query FROM pet_table
        """.trimIndent())

                db.execSQL("DROP TABLE pet_table")

                db.execSQL("ALTER TABLE pet_table_new RENAME TO pet_table")

            }
        }
    }

