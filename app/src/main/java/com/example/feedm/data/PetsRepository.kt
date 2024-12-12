package com.example.feedm.data


import com.example.feedm.data.database.dao.PetDao
import com.example.feedm.data.database.entities.PetEntity
import com.example.feedm.data.local.PetLocalStorageProvider
import com.example.feedm.data.local.PetModel
import com.example.feedm.domain.model.Pet
import com.example.feedm.domain.model.toDomain
import javax.inject.Inject


class PetsRepository @Inject constructor(
    private val petsDao: PetDao,
    private val petsStorage: PetLocalStorageProvider
) {


//From Local Storage
    fun getAllPetsFromStorage(): List<Pet> {
        return petsStorage.getAllPets().map { it.toDomain() }
    }

    fun insertPetsToStorage(pets: List<PetModel>) {
        petsStorage.insertAll(pets)
    }

    fun deleteAllPetsFromStorage(){
        petsStorage.deleteAllPets()
    }


//From DataBase
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
        petsDao.deletePet(petEntity.id)
    }

    suspend fun updatePet(petEntity: PetEntity) {
        petsDao.updatePet(petEntity)
    }
}