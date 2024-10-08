package com.example.feedm.data.model


import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import java.io.File
import java.io.FileWriter


class PetsRepository(context: Context){


    private var petsFile = File(context.filesDir, "pets")
    private val gson = Gson()
    private var filled = false


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
            val listType: java.lang.reflect.Type = object : TypeToken<ArrayList<PetModel>>() {}.type
            PetProvider.pets = gson.fromJson(json,listType)
            filled = true
        }
        catch (_: NullPointerException){

        }

    }

    fun addPet(petModel: PetModel){
       PetProvider.pets.add(petModel)
        val json = gson.toJson(PetProvider.pets)
        FileWriter(petsFile).use { writer ->
            writer.write(json)
        }

    }

    fun deletePet(petModel: PetModel){
        FileWriter(petsFile).use { _->}
        PetProvider.pets.remove(petModel)
        val json = gson.toJson(PetProvider.pets)
        FileWriter(petsFile).use { writer ->
            writer.write(json)
        }
        fillPetList()
    }


    fun editPet(petModel: PetModel, pos: Int){
        Log.i("step6","gestorPets()")
        FileWriter(petsFile).use { _->}
        PetProvider.pets.remove(petModel)
        PetProvider.pets.add(pos,petModel)
        val json = gson.toJson(PetProvider.pets)
        FileWriter(petsFile).use { writer ->
            writer.write(json)
        }
        fillPetList()
    }

    fun getPet(pos: Int): PetModel{
        if(filled){
            return PetProvider.getPet(pos)
        }
        fillPetList()
        return PetProvider.getPet(pos)
    }

    fun getAllPets(): ArrayList<PetModel>{
        if(filled){
            return PetProvider.getAllPets()
        }
        fillPetList()
        return PetProvider.getAllPets()
    }


}