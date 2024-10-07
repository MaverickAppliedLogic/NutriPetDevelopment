package com.example.feedm.ui.view.managementClasses


import android.content.Context
import android.util.Log
import com.example.feedm.data.model.PetModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import java.io.File
import java.io.FileWriter


class PetsManager(context: Context){


    private var petsFile = File(context.filesDir, "pets")
    private val gson = Gson()
    private var petModelList = ArrayList<PetModel>()


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
            petModelList = gson.fromJson(json,listType)
        }
        catch (_: NullPointerException){

        }

    }

    fun addPet(petModel: PetModel){
       petModelList.add(petModel)
        val json = gson.toJson(petModelList)
        FileWriter(petsFile).use { writer ->
            writer.write(json)
        }

    }

    fun getPet(petId: Int): PetModel {
        return petModelList.get(petId)
    }

    fun deletePet(petModel: PetModel){
        FileWriter(petsFile).use { _->}
        petModelList.remove(petModel)
        val json = gson.toJson(petModelList)
        FileWriter(petsFile).use { writer ->
            writer.write(json)
        }
        fillPetList()
    }


    fun editPet(petModel: PetModel, pos: Int){
        Log.i("step6","gestorPets()")
        FileWriter(petsFile).use { _->}
        petModelList.remove(petModel)
        petModelList.add(pos,petModel)
        val json = gson.toJson(petModelList)
        FileWriter(petsFile).use { writer ->
            writer.write(json)
        }
        fillPetList()
    }

    fun getPetList() : ArrayList<PetModel>{
            return petModelList
        }



}