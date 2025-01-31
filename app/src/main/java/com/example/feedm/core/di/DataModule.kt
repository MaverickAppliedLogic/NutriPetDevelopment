package com.example.feedm.core.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.feedm.core.data.database.AppDatabase
import com.example.feedm.core.data.database.dao.FoodDao
import com.example.feedm.core.data.database.dao.MealDao
import com.example.feedm.core.data.database.dao.PetDao
import com.example.feedm.core.data.database.dao.PetFoodDao
import com.example.feedm.core.data.local.PetLocalStorageProvider
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
    fun provideFilesDir(@ApplicationContext context: Context): File {
        val petsFile = File(context.filesDir, "pets")
        if (!petsFile.exists()) {
            petsFile.createNewFile()
        }
        return petsFile
    }

    @Singleton
    @Provides
    fun petLocalStorage(file: File): PetLocalStorageProvider{
        return PetLocalStorageProvider(file)
    }

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, "app_database")
            .addMigrations(MIGRATION)
            .build()


    val MIGRATION = object : Migration(7, 8) {
        override fun migrate(db: SupportSQLiteDatabase) {
            // Crear las nuevas tablas con los índices adecuados

            // Crear la tabla "food_table_new"
            db.execSQL("""
            CREATE TABLE food_table_new (
                food_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                food_name TEXT NOT NULL,
                brand TEXT NOT NULL,
                food_weight REAL,
                calories REAL NOT NULL
            )
        """.trimIndent())

            // Crear la tabla "pet_table_new" con claves foráneas
            db.execSQL("""
            CREATE TABLE pet_table_new (
                pet_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                animal TEXT NOT NULL,
                pet_name TEXT NOT NULL,
                age REAL NOT NULL,
                pet_weight REAL NOT NULL,
                genre TEXT,
                sterilized INTEGER NOT NULL,
                activity TEXT,
                goal TEXT NOT NULL,
                allergies TEXT
            )
        """.trimIndent())

            // Crear la tabla "meal_table_new" con claves foráneas
            db.execSQL("""
            CREATE TABLE meal_table_new (
                meal_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                pet_id INTEGER NOT NULL,
                meal_time INTEGER NOT NULL,
                ration REAL NOT NULL,
                FOREIGN KEY(pet_id) REFERENCES pet_table(pet_id) ON DELETE NO ACTION
            )
        """.trimIndent())

            // Crear la tabla "pet_food_table" para la relación N:M
            db.execSQL("""
            CREATE TABLE pet_food_table (
                pet_id INTEGER NOT NULL,
                food_id INTEGER NOT NULL,
                PRIMARY KEY (pet_id, food_id),
                FOREIGN KEY(pet_id) REFERENCES pet_table(pet_id) ON DELETE CASCADE,
                FOREIGN KEY(food_id) REFERENCES food_table(food_id) ON DELETE CASCADE
            )
        """.trimIndent())

            // Migración de datos para food_table
            db.execSQL("""
            INSERT INTO food_table_new (food_id, food_name, brand, food_weight, calories)
            SELECT food_id, food_name, brand, food_weight, calories FROM food_table
        """.trimIndent())

            // Migración de datos para pet_table
            db.execSQL("""
            INSERT INTO pet_table_new (pet_id, animal, pet_name, age, pet_weight, genre, sterilized, activity, goal, allergies)
            SELECT pet_id, animal, pet_name, age, pet_weight, genre, sterilized, activity, goal, allergies FROM pet_table
        """.trimIndent())

            // Migración de datos para meal_table
            db.execSQL("""
            INSERT INTO meal_table_new (meal_id, pet_id, meal_time, ration)
            SELECT meal_id, pet_id, meal_time, ration FROM meal_table
        """.trimIndent())

            // Aquí no hay datos iniciales para migrar en pet_food, ya que es una nueva tabla de relación

            // Eliminar las tablas antiguas
            db.execSQL("DROP TABLE food_table")
            db.execSQL("DROP TABLE pet_table")
            db.execSQL("DROP TABLE meal_table")

            // Renombrar las tablas nuevas a los nombres originales
            db.execSQL("ALTER TABLE food_table_new RENAME TO food_table")
            db.execSQL("ALTER TABLE pet_table_new RENAME TO pet_table")
            db.execSQL("ALTER TABLE meal_table_new RENAME TO meal_table")
        }
    }

    @Provides
    fun providePetDao(db: AppDatabase): PetDao {
       return db.getPetDao()
    }

    @Provides
    fun provideMealDao(db: AppDatabase): MealDao{
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

