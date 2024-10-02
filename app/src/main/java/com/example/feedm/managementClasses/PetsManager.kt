package com.example.feedm.managementClasses


import android.content.Context
import android.util.Log
import com.example.feedm.data.Pet
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import java.io.File
import java.io.FileWriter


class PetsManager(context: Context){


    private var petsFile = File(context.filesDir, "pets")
    private val gson = Gson()
    private var petList = ArrayList<Pet>()


    init {
        if(!petsFile.exists()){
            petsFile.createNewFile()
        }
        else{
            fillPetList()
        }

    }

    private fun fillPetList(){
        try {
            val json = petsFile.readText()
            val listType: java.lang.reflect.Type = object : TypeToken<ArrayList<Pet>>() {}.type
            petList = gson.fromJson(json,listType)
        }
        catch (_: NullPointerException){

        }

    }

    fun addPet(pet: Pet){
       petList.add(pet)
        val json = gson.toJson(petList)
        FileWriter(petsFile).use { writer ->
            writer.write(json)
        }

    }

    fun getPet(petId: Int): Pet {
        return petList.get(petId)
    }

    fun deletePet(pet: Pet){
        FileWriter(petsFile).use { _->}
        petList.remove(pet)
        val json = gson.toJson(petList)
        FileWriter(petsFile).use { writer ->
            writer.write(json)
        }
        fillPetList()
    }


    fun editPet(pet: Pet, pos: Int){
        Log.i("step6","gestorPets()")
        FileWriter(petsFile).use { _->}
        petList.remove(pet)
        petList.add(pos,pet)
        val json = gson.toJson(petList)
        FileWriter(petsFile).use { writer ->
            writer.write(json)
        }
        fillPetList()
    }

    fun getPetList() : ArrayList<Pet>{
            return petList
        }



}