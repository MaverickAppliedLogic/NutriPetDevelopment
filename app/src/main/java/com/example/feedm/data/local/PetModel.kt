package com.example.feedm.data.local

import com.example.feedm.domain.model.Pet

data class PetModel (
    var id : Int,
    var animal: String,
    var nombre: String,
    var edad: String,
    var peso: Double,
    var sexo: String,
    var esterilizado: String,
    var actividad: String,
    var objetivo: String,
    var alergia: String = "Nada",
    var query: String

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PetModel

        return id == other.id
    }

    override fun hashCode(): Int {
        return id
    }
}


fun Pet.toStorage()=
    PetModel(id = id,
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


