package com.maverickapps.nutripet.petsFeature.data

import com.maverickapps.nutripet.core.data.database.dao.PetDao
import com.maverickapps.nutripet.core.data.database.entities.PetEntity
import com.maverickapps.nutripet.core.data.local.PetLocalStorageProvider
import com.maverickapps.nutripet.petsFeature.domain.objectTasks.pet.model.PetModel
import com.maverickapps.nutripet.petsFeature.domain.objectTasks.pet.model.toDomain
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

    suspend fun getPetById(petId: Int): PetModel {
        return petsDao.getPetById(petId).toDomain()
    }

    suspend fun insertPetsToDB(pets: List<PetEntity>) {
        petsDao.insertAll(pets)
    }

    suspend fun insertPet(petEntity: PetEntity) {
        petsDao.insertPet(petEntity)
    }

    suspend fun deletePet(petId: Int) {
        petsDao.deletePet(petId)
    }

    suspend fun updatePet(petEntity: PetEntity) {
        petsDao.updatePet(petEntity)
    }
}