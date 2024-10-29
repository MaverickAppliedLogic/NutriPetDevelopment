package com.example.feedm.data.local

import android.util.Log
import com.example.feedm.domain.model.Pet
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
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
        return pets.sortedBy { it.id }
    }

    fun insertAll(petsList: List<PetModel>) {
        val json = gson.toJson(petsList)
        FileWriter(petsFile).use { writer ->
            writer.write(json)
        }

    }


    fun deletePet(pet: PetModel){
        val pets = getAllPets().toMutableList().apply { remove(pet) }
        val json = gson.toJson(pets)
        FileWriter(petsFile).use { writer ->
            writer.write(json)
        }
    }

}