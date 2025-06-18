package com.maverickapps.nutripet.petsFeature.domain.objectTasks.pet.useCase

import com.maverickapps.nutripet.core.data.database.entities.toDataBase
import com.maverickapps.nutripet.petsFeature.data.PetsRepository
import com.maverickapps.nutripet.petsFeature.domain.objectTasks.pet.model.PetModel
import javax.inject.Inject

class GetPetsUseCase @Inject constructor(private val repository: PetsRepository){
    suspend operator fun invoke(): List<PetModel>{
        var pets = repository.getAllPetsFromDB()
        return if (pets.isNullOrEmpty().not()){
            repository.insertPetsToStorage(pets)
            pets
        }
        else{
            pets = repository.getAllPetsFromStorage()
            if(pets.isNullOrEmpty().not()){
                repository.insertPetsToDB(pets.map { it.toDataBase() })
                //Se vuelve a recoger de la DB para que se les asignen ID's
                pets = repository.getAllPetsFromDB()
            }
            else pets = emptyList()
            pets
        }

    }
}