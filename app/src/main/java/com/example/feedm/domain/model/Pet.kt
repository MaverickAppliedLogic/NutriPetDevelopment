package com.example.feedm.domain.model

import com.example.feedm.data.database.entities.PetEntity
import com.example.feedm.data.local.PetModel

data class Pet(
    var id: Int,
    var animal: String,
    var nombre: String,
    var edad: String,
    var peso: Double,
    var sexo: String,
    var esterilizado: String,
    var actividad: String,
    var objetivo: String,
    var alergia: String = "Nada",
    var query: String) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Pet

        return id == other.id
    }

    override fun hashCode(): Int {
        return id
    }
}


fun PetModel.toDomain()=
    Pet(id,animal, nombre, edad, peso, sexo, esterilizado, actividad, objetivo, alergia, query)

fun PetEntity.toDomain()=
    Pet(id,animal, nombre, edad, peso, sexo, esterilizado, actividad, objetivo, alergia, query)

