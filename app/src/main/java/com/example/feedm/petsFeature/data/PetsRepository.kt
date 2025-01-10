package com.example.feedm.petsFeature.data

import com.example.feedm.core.data.database.dao.PetDao
import com.example.feedm.core.data.database.entities.PetEntity
import com.example.feedm.core.data.local.PetLocalStorageProvider
import com.example.feedm.core.domain.model.PetModel
import com.example.feedm.core.domain.model.toDomain
import javax.inject.Inject


class PetsRepository @Inject constructor(
    private val petsDao: PetDao,
    private val petsStorage: PetLocalStorageProvider
) {


//Local Storage
    fun getAllPetsFromStorage(): List<PetModel> {
        return petsStorage.getAllPets()
    }

    fun insertPetsToStorage(pets: List<PetModel>) {
        petsStorage.insertAll(pets)
    }

    fun deleteAllPetsFromStorage(){
        petsStorage.deleteAllPets()
    }


//DataBase
    suspend fun getAllPetsFromDB(): List<PetModel> {
        return petsDao.getAllPets().map { it.toDomain() }
    }

    suspend fun getPetById(petId: Int): PetModel{
        return petsDao.getPetById(petId).toDomain()
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