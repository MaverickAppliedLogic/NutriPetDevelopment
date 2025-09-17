package com.maverickapps.nutripet.core.data.room

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION = object : Migration(11, 13) {
    override fun migrate(db: SupportSQLiteDatabase) {
        // Crear las nuevas tablas con los índices adecuados

        db.execSQL("""
            CREATE TABLE food_table_new (
                food_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                food_name TEXT NOT NULL,
                brand TEXT NOT NULL,
                food_weight REAL,
                calories REAL NOT NULL
            )
        """.trimIndent())

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

        db.execSQL("""
            CREATE TABLE meal_table_new (
                meal_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                pet_id INTEGER NOT NULL,
                food_id INTEGER DEFAULT NULL,
                meal_time INTEGER NOT NULL,
                ration REAL NOT NULL,
                meal_state INTEGER NOT NULL,
                meal_calories REAL NOT NULL,
                is_daily_meal INTEGER NOT NULL,
                FOREIGN KEY(pet_id) REFERENCES pet_table(pet_id) ON DELETE CASCADE,
                FOREIGN KEY(food_id) REFERENCES food_table(food_id) ON DELETE SET DEFAULT
            )
        """.trimIndent())

        db.execSQL("""
            CREATE TABLE pet_food_table_new_new (
                pet_id INTEGER NOT NULL,
                food_id INTEGER NOT NULL,
                PRIMARY KEY (pet_id, food_id),
                FOREIGN KEY(pet_id) REFERENCES pet_table(pet_id) ON DELETE CASCADE,
                FOREIGN KEY(food_id) REFERENCES food_table(food_id) ON DELETE CASCADE
            )
        """.trimIndent())

        // Migración de datos
        db.execSQL("""
            INSERT INTO food_table_new (food_id, food_name, brand, food_weight, calories)
            SELECT food_id, food_name, brand, food_weight, calories FROM food_table
        """.trimIndent())

        db.execSQL("""
            INSERT INTO pet_table_new (pet_id, animal, pet_name, age, pet_weight, genre, sterilized, activity, goal, allergies)
            SELECT pet_id, animal, pet_name, age, pet_weight, genre, sterilized, activity, goal, allergies FROM pet_table
        """.trimIndent())

        db.execSQL("""
            INSERT INTO pet_food_table_new_new (pet_id, food_id)
            SELECT pet_id, food_id FROM pet_food_table
        """.trimIndent())

        db.execSQL("""
            INSERT INTO meal_table_new (meal_id, pet_id, food_id, meal_time, ration, meal_calories, is_daily_meal)
            SELECT meal_id, pet_id, food_id, meal_time, ration, meal_calories, 1 FROM meal_table
        """.trimIndent())

        // Eliminar las tablas antiguas
        db.execSQL("DROP TABLE food_table")
        db.execSQL("DROP TABLE pet_table")
        db.execSQL("DROP TABLE meal_table")
        db.execSQL("DROP TABLE pet_food_table")

        // Renombrar las tablas nuevas a los nombres originales
        db.execSQL("ALTER TABLE food_table_new RENAME TO food_table")
        db.execSQL("ALTER TABLE pet_table_new RENAME TO pet_table")
        db.execSQL("ALTER TABLE meal_table_new RENAME TO meal_table")
        db.execSQL("ALTER TABLE pet_food_table_new_new RENAME TO pet_food_table")
    }
}