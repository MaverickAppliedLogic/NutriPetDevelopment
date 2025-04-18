package com.example.feedm.petsFeature.domain.objectTasks.pet.util

import com.example.feedm.petsFeature.domain.objectTasks.pet.model.PetModel
import javax.inject.Inject


class PetValidator @Inject constructor() {

    fun validatePet(pet: PetModel): Pair<Boolean, String> {

        if (validateName(pet.petName).not()) {
            return Pair(false, "Name")
        }
        if (validateAnimal(pet.animal).not()) {
            return Pair(false, "Animal")
        }
        if (validateAge(pet.age).not()) {
            return Pair(false, "Age")
        }

        if (validateWeight(pet.petWeight).not()) {
            return Pair(false, "Weight")
        }

        if (validateGoal(pet.goal).not()) {
            return Pair(false, "Goal")
        }

        if (pet.allergies.isNullOrBlank()) {
            pet.allergies = "No allergies registered"
        }

        return Pair(true, "")
    }

    private fun validateName(name: String) : Boolean {
        return name.isNotBlank()
    }

    private fun validateAnimal(animal: String) : Boolean {
        if (animal != "Dog" && animal != "Cat") {
            return false
        }
        return true
    }

   private fun validateAge(age: Float) : Boolean {
        if (age <= 1f) {
            return false
        }
        return true
    }

    private fun validateWeight(weight: Float) : Boolean {
        if (weight <= 1f) {
            return false
        }
        return true
    }

    private fun validateGoal(goal: String) : Boolean {
        return goal.isNotBlank()
    }
}
