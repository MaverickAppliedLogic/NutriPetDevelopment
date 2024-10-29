package com.example.feedm.domain

import android.util.Log
import com.example.feedm.data.PetsRepository
import com.example.feedm.data.database.entities.toDataBase
import com.example.feedm.data.local.toStorage
import com.example.feedm.domain.model.Pet
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
            pets
        }

    }
}