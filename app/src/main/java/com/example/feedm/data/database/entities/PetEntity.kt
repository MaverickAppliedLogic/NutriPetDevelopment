package com.example.feedm.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.feedm.domain.model.Pet

@Entity(tableName = "pet_table")
data class PetEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")val id: Int = 0,
    @ColumnInfo(name = "animal")val animal: String,
    @ColumnInfo(name = "name")val nombre: String,
    @ColumnInfo(name = "age")val edad: String,
    @ColumnInfo(name = "weight")val peso: Double,
    @ColumnInfo(name = "genre")val sexo: String,
    @ColumnInfo(name = "esterilized")val esterilizado: String,
    @ColumnInfo(name = "activity")val actividad: String,
    @ColumnInfo(name = "goal")val objetivo: String,
    @ColumnInfo(name = "allergies")val alergia: String = "Nada",
    @ColumnInfo(name = "query")val query: String
)

fun Pet.toDataBase() =
    PetEntity(
        id = id,
        animal =  animal,
        nombre =  nombre,
        edad = edad,
        peso =  peso,
        sexo = sexo,
        esterilizado = esterilizado,
        actividad =  actividad,
        objetivo =  objetivo,
        alergia =  alergia,
        query = query)