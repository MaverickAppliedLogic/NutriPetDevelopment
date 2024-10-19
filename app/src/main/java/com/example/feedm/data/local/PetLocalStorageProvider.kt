package com.example.feedm.data.local

import android.util.Log
import com.example.feedm.data.model.PetModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.FileWriter
import javax.inject.Inject

class PetLocalStorageProvider (private val petsFile: File) {

    private val gson = Gson()

    fun getPetsList(): ArrayList<PetModel> {
        var pets : ArrayList<PetModel> = ArrayList()
        try {
            val json = petsFile.readText()
            val listType: java.lang.reflect.Type = object : TypeToken<ArrayList<PetModel>>() {}.type
            pets = gson.fromJson(json, listType)
        } catch (_: NullPointerException) { }
        return pets
    }

    fun updatePetsList(petsList: ArrayList<PetModel>) {
        val json = gson.toJson(petsList)
        FileWriter(petsFile).use { writer ->
                writer.write(json)
        }
    }



}