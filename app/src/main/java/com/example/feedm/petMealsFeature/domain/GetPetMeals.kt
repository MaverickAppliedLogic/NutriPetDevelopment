package com.example.feedm.petMealsFeature.domain

import com.example.feedm.petMealsFeature.data.PetMealsRepository
import com.example.feedm.petMealsFeature.domain.model.PetMealsModel
import javax.inject.Inject


class GetPetMeals @Inject constructor(private val repository: PetMealsRepository)  {


    suspend operator fun invoke(petId: Int): PetMealsModel{
        val pet = repository.getPetById(petId)
        val meals = repository.getAllPetMeals(petId)


        return PetMealsModel(pet, meals)
    }
}