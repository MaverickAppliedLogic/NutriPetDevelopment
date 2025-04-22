package com.example.feedm.petsFeature.domain.objectTasks.pet.util

import com.example.feedm.petsFeature.domain.objectTasks.pet.model.PetModel
import com.example.feedm.petsFeature.ui.view.screens.addPetScreen.utils.FormItemsInteractionsHandler.Companion.ACTIVITY_FIELD
import com.example.feedm.petsFeature.ui.view.screens.addPetScreen.utils.FormItemsInteractionsHandler.Companion.AGE_FIELD
import com.example.feedm.petsFeature.ui.view.screens.addPetScreen.utils.FormItemsInteractionsHandler.Companion.GOAL_FIELD
import com.example.feedm.petsFeature.ui.view.screens.addPetScreen.utils.FormItemsInteractionsHandler.Companion.PET_NAME_FIELD
import com.example.feedm.petsFeature.ui.view.screens.addPetScreen.utils.FormItemsInteractionsHandler.Companion.SEX_FIELD
import com.example.feedm.petsFeature.ui.view.screens.addPetScreen.utils.FormItemsInteractionsHandler.Companion.STERILIZED_FIELD
import com.example.feedm.petsFeature.ui.view.screens.addPetScreen.utils.FormItemsInteractionsHandler.Companion.WEIGHT_FIELD
import javax.inject.Inject


class PetValidator @Inject constructor() {

    fun validatePet(pet: PetModel): List<Int> {
        val validFields = mutableListOf(
            PET_NAME_FIELD,
            AGE_FIELD,
            SEX_FIELD,
            WEIGHT_FIELD,
            GOAL_FIELD,
            STERILIZED_FIELD,
            ACTIVITY_FIELD
        )

        if (validateName(pet.petName).not()) {
            validFields.remove(PET_NAME_FIELD)
        }
        if (validateAge(pet.age).not()) {
            validFields.remove(AGE_FIELD)
        }
        if (pet.genre.isNullOrBlank()) {
            validFields.remove(SEX_FIELD)
        }
        if (validateWeight(pet.petWeight).not()) {
            validFields.remove(WEIGHT_FIELD)
        }
        if (validateGoal(pet.goal).not()) {
            validFields.remove(GOAL_FIELD)
        }
        if (!pet.sterilized) {
            validFields.remove(STERILIZED_FIELD)
        }
        if (validateActivity(pet.activity)) {
            validFields.remove(ACTIVITY_FIELD)
        }
        if (pet.allergies.isNullOrBlank()) {
            pet.allergies = "No allergies registered"
        }

        return validFields
    }

    private fun validateName(name: String): Boolean {
        return name.isNotBlank()
    }

    private fun validateAge(age: Float): Boolean {
        if (age < 0.5f) {
            return false
        }
        return true
    }

    private fun validateWeight(weight: Float): Boolean {
        if (weight < 1f) {
            return false
        }
        return true
    }

    private fun validateGoal(goal: String): Boolean {
        return goal.isNotBlank()
    }

    private fun validateActivity(activity: String?): Boolean {
        return activity.isNullOrBlank()
    }
}
