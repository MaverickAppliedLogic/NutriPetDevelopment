package com.maverickapps.tailyCare.petsFeature.domain.objectTasks.pet.util

import com.maverickapps.tailyCare.petsFeature.domain.objectTasks.pet.model.PetModel
import com.maverickapps.tailyCare.petsFeature.ui.view.screens.registerPetScreen.utils.FormItemsInteractionsHandler.Companion.ACTIVITY_FIELD
import com.maverickapps.tailyCare.petsFeature.ui.view.screens.registerPetScreen.utils.FormItemsInteractionsHandler.Companion.AGE_FIELD
import com.maverickapps.tailyCare.petsFeature.ui.view.screens.registerPetScreen.utils.FormItemsInteractionsHandler.Companion.GOAL_FIELD
import com.maverickapps.tailyCare.petsFeature.ui.view.screens.registerPetScreen.utils.FormItemsInteractionsHandler.Companion.PET_NAME_FIELD
import com.maverickapps.tailyCare.petsFeature.ui.view.screens.registerPetScreen.utils.FormItemsInteractionsHandler.Companion.WEIGHT_FIELD
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import org.junit.Before
import org.junit.Test

class PetValidatorTest {

    private lateinit var petValidator: PetValidator

    @Before
    fun setUp() {
        petValidator = PetValidator()
    }

    @Test
    fun `validatePet should return false with Name error when petName is empty`() {
        val pet = PetModel(
            petId = 1,
            animal = "Dog",
            petName = "",
            age = 5f,
            petWeight = 10f,
            genre = "Male",
            sterilized = true,
            activity = "Walking",
            goal = "Healthy",
            allergies = "None"
        )

        val result = petValidator.validatePet(pet)

        assertFalse(result.contains(PET_NAME_FIELD))
    }

    @Test
    fun `validatePet should return false with Age error when age is less than 0,5`() {
        val pet = PetModel(
            petId = 2,
            animal = "Cat",
            petName = "Whiskers",
            age = 0f,
            petWeight = 10f,
            genre = "Female",
            sterilized = false,
            activity = "Sleeping",
            goal = "Gain weight",
            allergies = "None"
        )

        val result = petValidator.validatePet(pet)

        assertFalse(result.contains(AGE_FIELD))
    }

    @Test
    fun `validatePet should return false with Weight error when weight is less than 1`() {
        val pet = PetModel(
            petId = 3,
            animal = "Dog",
            petName = "Charlie",
            age = 5f,
            petWeight = 0.9f,
            genre = "Male",
            sterilized = true,
            activity = "Running",
            goal = "Maintain weight",
            allergies = "Grass allergy"
        )

        val result = petValidator.validatePet(pet)

        assertFalse(result.contains(WEIGHT_FIELD))
    }

    @Test
    fun `validatePet should return false with Goal error when goal is empty`() {
        val pet = PetModel(
            petId = 4,
            animal = "Cat",
            petName = "Tom",
            age = 5f,
            petWeight = 4f,
            genre = "Female",
            sterilized = false,
            activity = "Jumping",
            goal = "",
            allergies = "None"
        )

        val result = petValidator.validatePet(pet)

        assertFalse(result.contains(GOAL_FIELD))
    }

    @Test
    fun `validatePet should return false with Activity error when activity is not valid`() {
        val pet = PetModel(
            petId = 5,
            animal = "Dog",
            petName = "Max",
            age = 5f,
            petWeight = 10f,
            genre = "Male",
            sterilized = true,
            activity = null,
            goal = "Lose weight",
            allergies = "No allergies"
        )

        val result = petValidator.validatePet(pet)

        assertFalse(result.contains(ACTIVITY_FIELD))
    }

    @Test
    fun `validatePet should return true when all fields are valid`() {
        val pet = PetModel(
            petId = 6,
            animal = "Dog",
            petName = "Buddy",
            age = 5f,
            petWeight = 10f,
            genre = "Male",
            sterilized = true,
            activity = "Playing",
            goal = "Stay active",
            allergies = "None"
        )

        val result = petValidator.validatePet(pet)

        assertFalse(result.isEmpty())
    }

    @Test
    fun `validatePet should set allergies to default value when allergies are null or blank`() {
        val pet = PetModel(
            petId = 7,
            animal = "Cat",
            petName = "Tom",
            age = 3f,
            petWeight = 4f,
            genre = "Male",
            sterilized = false,
            activity = "Sleeping",
            goal = "Maintain health",
            allergies = null
        )

        petValidator.validatePet(pet)

        assertEquals("No allergies registered", pet.allergies)
    }
}
