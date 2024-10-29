package com.example.feedm.data.cache

import com.example.feedm.data.local.PetModel

class PetCacheProvider {
    private var pets = ArrayList<PetModel>()

    fun fillList(petsList: ArrayList<PetModel>){
        pets = petsList
    }

    fun addPet(petModel: PetModel){
        pets.add(petModel)
    }

    fun deletePet(petModel: PetModel){
        pets.remove(petModel)
    }


    fun editPet(petModel: PetModel, pos: Int){
        pets.remove(petModel)
        pets.add(pos,petModel)
    }

    fun getPet(pos: Int): PetModel {
        return pets[pos]
    }

    fun getAllPets(): List<PetModel>{
        return pets
    }

}