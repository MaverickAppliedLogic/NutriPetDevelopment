package com.example.feedm.petsFeature.domain

import android.util.Log
import com.example.feedm.core.database.entities.toDataBase
import com.example.feedm.petsFeature.data.local.toStorage
import com.example.feedm.petsFeature.data.PetsRepository
import com.example.feedm.petsFeature.domain.model.Pet
import javax.inject.Inject

class GetPets @Inject constructor(private val repository: PetsRepository){
    suspend operator fun invoke(): List<Pet>{
        var pets = repository.getAllPetsFromDB()
        return if (pets.isNotEmpty()){
            repository.insertPetsToStorage(pets.map { it.toStorage() })
            Log.i("Depuring","Vallidacion pets is not empty")
            pets
        }
        else{
            Log.i("Depuring","Vallidacion pets is empty")
            pets = repository.getAllPetsFromStorage()
            repository.insertPetsToDB(pets.map { it.toDataBase() })
            //Se vuelve a recoger de la DB para que se les asignen ID's
            pets = repository.getAllPetsFromDB()
            pets
        }

    }
}