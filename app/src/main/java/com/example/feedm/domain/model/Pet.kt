package com.example.feedm.domain.model

import com.example.feedm.data.database.entities.PetEntity
import com.example.feedm.data.local.PetModel


data class Pet(
    var id: Int = 0,
    var animal: String = "No Animal",
    var nombre: String = "No Name",
    var edad: String = "No Age",
    var peso: Double = 0.0,
    var sexo: String = "No Sex",
    var esterilizado: String = "No Sterilized",
    var actividad: String = "No Activity",
    var objetivo: String = "No Objective",
    var alergia: String = "Nada",
    var query: String = "No Query")




fun PetModel.toDomain()=
    Pet(animal=animal, nombre=nombre, edad=edad, peso=peso, sexo=sexo, esterilizado=esterilizado,
        actividad=actividad, objetivo=objetivo, alergia=alergia, query=query)

fun PetEntity.toDomain()=
    Pet(id,animal, nombre, edad, peso, sexo, esterilizado, actividad, objetivo, alergia, query)

