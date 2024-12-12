package com.example.feedm.data.local

import com.example.feedm.domain.model.Pet

data class PetModel (
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

)


fun Pet.toStorage()=
    PetModel(
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


