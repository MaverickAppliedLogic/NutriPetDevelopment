package com.example.feedm.data


import com.example.feedm.data.cache.PetCacheProvider
import com.example.feedm.data.local.PetLocalStorageProvider
import com.example.feedm.data.model.PetModel
import javax.inject.Inject


class PetsRepository @Inject constructor(
    private val petsLocalStorage: PetLocalStorageProvider,
    private val petsCache : PetCacheProvider){

    private var isCacheFilled = false

    init {
        fillPetList()
    }

    private fun fillPetList(){
        petsCache.fillList(petsLocalStorage.getPetsList())
        isCacheFilled = petsLocalStorage.getPetsList().isNotEmpty()
    }

    fun addPet(petModel: PetModel){
        petsCache.addPet(petModel)
        petsLocalStorage.updatePetsList(petsCache.getAllPets())
        isCacheFilled = true
    }

    fun deletePet(petModel: PetModel){
        petsCache.deletePet(petModel)
        petsLocalStorage.updatePetsList(petsCache.getAllPets())
        isCacheFilled = petsCache.getAllPets().isNotEmpty()
    }


    fun editPet(petModel: PetModel, pos: Int){
        petsCache.editPet(petModel,pos)
        petsLocalStorage.updatePetsList(petsCache.getAllPets())
    }

    fun getAllPets(): ArrayList<PetModel>{
        if (!isCacheFilled){
            fillPetList()
        }
        return petsCache.getAllPets()
    }


}