package com.example.feedm.petsFeature.domain.objectTasks.pet.useCase

import com.example.feedm.petsFeature.data.PetsRepository
import com.example.feedm.petsFeature.domain.objectTasks.pet.model.PetModel
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class RegisterPetUseCaseTest {

    private lateinit var repository: PetsRepository
    private lateinit var registerPetUseCase: RegisterPetUseCase

    @Before
    fun setUp() {
        repository = mockk()
        registerPetUseCase = RegisterPetUseCase(repository)
    }

    @Test
    fun `should update pet if pet already exists`() = runBlocking {
        // Given
        val petModel = PetModel(
            petId = 123,
            animal = "Dog",
            petName = "Firulais",
            age = 3.5f,
            petWeight = 12.3f,
            genre = "Male",
            sterilized = true,
            activity = "High",
            goal = "Healthy",
            allergies = "None"
        )

        coEvery { repository.getPetById(petModel.petId) } returns petModel
        coEvery { repository.updatePet(any()) } just Runs

        // When
        registerPetUseCase.invoke(petModel)

        // Then
        coVerify(exactly = 1) { repository.getPetById(petModel.petId) }
        coVerify(exactly = 1) { repository.updatePet(any()) }
        coVerify(exactly = 0) { repository.insertPet(any()) }
    }

    @Test
    fun `should insert pet if pet does not exist`() = runBlocking {
        // Given
        val petModel = PetModel(
            petId = 456,
            animal = "Cat",
            petName = "Whiskers",
            age = 2.0f,
            petWeight = 4.5f,
            genre = "Female",
            sterilized = false,
            activity = "Medium",
            goal = "Maintain weight",
            allergies = "None"
        )
        val petModelFake = PetModel(
            petId = -1,
            animal = "Cat",
            petName = "Whiskers",
            age = 2.0f,
            petWeight = 4.5f,
            genre = "Female",
            sterilized = false,
            activity = "Medium",
            goal = "Maintain weight",
            allergies = "None"
        )

        coEvery { repository.getPetById(petModel.petId) } returns petModelFake
        coEvery { repository.insertPet(any()) } just Runs

        // When
        registerPetUseCase.invoke(petModel)

        // Then
        coVerify(exactly = 1) { repository.getPetById(petModel.petId) }
        coVerify(exactly = 0) { repository.updatePet(any()) }
        coVerify(exactly = 1) { repository.insertPet(any()) }
    }
}
