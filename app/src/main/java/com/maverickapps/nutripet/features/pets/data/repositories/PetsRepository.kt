package com.maverickapps.nutripet.features.pets.data.repositories

import com.maverickapps.nutripet.core.data.room.dao.PetDao
import com.maverickapps.nutripet.core.data.room.entities.PetEntity
import com.maverickapps.nutripet.features.pets.data.datastore.PetDatastore
import com.maverickapps.nutripet.features.pets.domain.objectTasks.pet.model.PetModel
import com.maverickapps.nutripet.features.pets.domain.objectTasks.pet.model.toDomain
import javax.inject.Inject


class PetsRepository @Inject constructor(
    private val petsDao: PetDao,
    private val petsStorage: PetDatastore
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