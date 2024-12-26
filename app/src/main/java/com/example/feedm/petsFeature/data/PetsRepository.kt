package com.example.feedm.petsFeature.data

import com.example.feedm.core.database.dao.PetDao
import com.example.feedm.core.database.entities.PetEntity
import com.example.feedm.petsFeature.data.local.PetLocalStorageProvider
import com.example.feedm.petsFeature.data.local.PetModel
import com.example.feedm.petsFeature.domain.model.Pet
import com.example.feedm.petsFeature.domain.model.toDomain
import javax.inject.Inject


class PetsRepository @Inject constructor(
    private val petsDao: PetDao,
    private val petsStorage: PetLocalStorageProvider
) {


//Local Storage
    fun getAllPetsFromStorage(): List<Pet> {
        return petsStorage.getAllPets().map { it.toDomain() }
    }

    fun insertPetsToStorage(pets: List<PetModel>) {
        petsStorage.insertAll(pets)
    }

    fun deleteAllPetsFromStorage(){
        petsStorage.deleteAllPets()
    }


//DataBase
    suspend fun getAllPetsFromDB(): List<Pet> {
        return petsDao.getAllPets().map { it.toDomain() }
    }

    suspend fun insertPetsToDB(pets: List<PetEntity>) {
        petsDao.insertAll(pets)
    }

    suspend fun insertPet(petEntity: PetEntity) {
        petsDao.insertPet(petEntity)
    }

    suspend fun deletePet(petEntity: PetEntity) {
        petsDao.deletePet(petEntity.petId)
    }

    suspend fun updatePet(petEntity: PetEntity) {
        petsDao.updatePet(petEntity)
    }
}