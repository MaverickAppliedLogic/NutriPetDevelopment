package com.example.feedm.core.utils

import android.util.Log
import com.example.feedm.core.domain.model.PetModel
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class CaloriesCalculatorDogTest{
    private lateinit var caloriesCalculator: CaloriesCalculatorDog

    @Before
    fun setUp(){
        caloriesCalculator = CaloriesCalculatorDog()
    }



    /**
     * Calculate the calories needed for an `adult` dog, that `weights 15kg`, has
     * a `high activity level` wants to `gain weight` and is `sterilized`.
     * The result should being somewhere about 10% difference of 987:
     * ```
     * result in 888.3..1085.7
     * ```
     */
    @Test
    fun `it should return a result about 10 percent difference from 987kcal`(){
        //Given
        val pet = PetModel(
            -1,
            null,
            "dog",
            "",
            6.0f,
            15.0f,
            null,
            true,
            "Alta",
            "Aumento de peso",
            null
        )

        //When
        val result = caloriesCalculator.calculateCalories(pet)

        //Then
        println("result 987 is:$result")
        assert(result in 888.3..1085.7)

    }


    /**
     * Calculate the calories needed for a `puppy` dog, that `weights 5kg`, has
     * a `medium activity level` wants to `maintain weight` and is `sterilized`.
     * The result should being somewhere about 10% difference of 496:
     * ```
     * result in 446.4..545.6
     * ```
     */
    @Test
    fun `it should return a result about 10 percent difference from 496kcal`(){
        //Given
        val pet = PetModel(
            -1,
            null,
            "dog",
            "",
            0.5f,
            5.0f,
            null,
            true,
            "Media",
            "Mantenimiento",
            null
        )

        //When
        val result = caloriesCalculator.calculateCalories(pet)

        //Then
        println("result 496 is:$result")
        assert(result in 446.4..545.6)

    }


    /**
     * Calculate the calories needed for an `senior` dog, that `weights 85kg`, has
     * a `low activity level` wants to `maintain weight` and is `sterilized`.
     * The result should being somewhere about 10% difference of 2064:
     * ```
     * result in 1857.6..2270.4
     * ```
     */
    @Test
    fun `it should return a result about 10 percent difference from 2064kcal`(){
        //Given
        val pet = PetModel(
            -1,
            null,
            "dog",
            "",
            8.0f,
            85.0f,
            null,
            true,
            "Baja",
            "Mantenimiento",
            null
        )

        //When
        val result = caloriesCalculator.calculateCalories(pet)

        //Then
        println("result 2064 is:$result")
        assert(result in 1857.6..2270.4)

    }


    /**
     * Calculate the calories needed for an `senior` dog, that `weights 5kg`, has
     * a `medium activity level` wants to `maintain weight` and is ` not sterilized`.
     * The result should being somewhere about 10% difference of 356:
     * ```
     * result in 320.4..391.6
     * ```
     */
    @Test
    fun `it should return a result about 10 percent difference from 356kcal`(){
        //Given
        val pet = PetModel(
            -1,
            null,
            "dog",
            "",
            8.0f,
            5.0f,
            null,
            false,
            "Media",
            "Mantenimiento",
            null
        )

        //When
        val result = caloriesCalculator.calculateCalories(pet)

        //Then
        println("result 356 is:$result")
        assert(result in 320.4..391.6)

    }


    /**
     * Calculate the calories needed for an `adult` dog, that `weights 50kg`, has
     * `not registered activity level` wants to `maintain weight` and is ` not sterilized`.
     * The result should being somewhere about 10% difference of 1911:
     * ```
     * result in 1719.9..2102.1
     * ```
     */
    @Test
    fun `it should return a result about 10 percent difference from 1911kcal`(){
        //Given
        val pet = PetModel(
            -1,
            null,
            "dog",
            "",
            5.0f,
            50.0f,
            null,
            false,
            null,
            "Mantenimiento",
            null
        )

        //When
        val result = caloriesCalculator.calculateCalories(pet)

        //Then
        println("result 1911 is:$result")
        assert(result in 1719.9..2102.1)

    }
}