package com.maverickapps.nutripet.core.data.localStorage


import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.maverickapps.nutripet.petsFeature.domain.objectTasks.pet.model.PetModel
import java.io.File
import java.io.FileWriter

class PetLocalStorageProvider(private val petsFile: File) {

    private val gson = Gson()

    //Full List
    fun getAllPets(): List<PetModel> {
        val pets: List<PetModel>
        try {
            val json = petsFile.readText()
            val listType: java.lang.reflect.Type = object : TypeToken<List<PetModel>>() {}.type
            pets = gson.fromJson(json, listType)
        } catch (_: NullPointerException) {
            return emptyList()
        }
        return pets
    }

    fun insertAll(petsList: List<PetModel>) {
        val json = gson.toJson(petsList)
        FileWriter(petsFile).use { writer ->
            writer.write(json)
        }

    }


    fun deleteAllPets() {
        FileWriter(petsFile).use { writer ->
            writer.write("")
        }

    }

}