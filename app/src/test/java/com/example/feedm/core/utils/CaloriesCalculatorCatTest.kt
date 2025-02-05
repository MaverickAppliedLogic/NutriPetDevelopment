package com.example.feedm.core.utils

import com.example.feedm.petsFeature.domain.model.PetModel
import org.junit.Before
import org.junit.Test

class CaloriesCalculatorCatTest{

    private lateinit var caloriesCalculator: CaloriesCalculatorCat

    @Before
    fun setUp(){
        caloriesCalculator = CaloriesCalculatorCat()
    }

    /**
     * Calculate the calories needed for an `young` cat, that `weights 9kg`, has
     * a `medium activity level` wants to `maintain weight` and is `not sterilized`.
     * The result should being somewhere about 10% difference of 504:
     * ```
     * result in 453.6..554.4
     * ```
     */
    @Test
    fun `it should return a result about 10 percent difference from 504kcal`(){
        //Given
        val pet = PetModel(
            -1,
            "cat",
            "",
            4.0f,
            9.0f,
            null,
            false,
            "Media",
            "Mantenimiento",
            null
        )

        //When
        val result = caloriesCalculator.calculateCalories(pet)

        //Then
        println("result 504 is:$result")
        assert(result in 453.6..554.4)

    }


    /**
     * Calculate the calories needed for a `senior` cat, that `weights 5kg`, has
     * a `medium activity level` wants to `maintain weight` and is `sterilized`.
     * The result should being somewhere about 10% difference of 225:
     * ```
     * result in 202.5..247.5
     * ```
     */
    @Test
    fun `it should return a result about 10 percent difference from 225kcal`(){
        //Given
        val pet = PetModel(
            -1,
            "cat",
            "",
            8.0f,
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
        println("result 225 is:$result")
        assert(result in 202.5..247.5)

    }


    /**
     * Calculate the calories needed for an `young` cat, that `weights 4kg`, has
     * a `low activity level` wants to `maintain weight` and is `sterilized`.
     * The result should being somewhere about 10% difference of 238:
     * ```
     * result in 180.0..220.0
     * ```
     */
    @Test
    fun `it should return a result about 10 percent difference from 200kcal`(){
        //Given
        val pet = PetModel(
            -1,
            "cat",
            "",
            3.0f,
            4.0f,
            null,
            true,
            "Baja",
            "Mantenimiento",
            null
        )

        //When
        val result = caloriesCalculator.calculateCalories(pet)

        //Then
        println("result 200 is:$result")
        assert(result in 180.0..220.0)

    }

    /**
     * Calculate the calories needed for an `young` cat, that `weights 4kg`, has
     * a `low activity level` wants to `maintain weight` and is ` not sterilized`.
     * The result should being somewhere about 10% difference of 238:
     * ```
     * result in 189.0..231.0
     * ```
     */
    @Test
    fun `it should return a result about 10 percent difference from 210kcal`(){
        //Given
        val pet = PetModel(
            -1,
            "cat",
            "",
            3.0f,
            4.0f,
            null,
            false,
            "Baja",
            "Mantenimiento",
            null
        )

        //When
        val result = caloriesCalculator.calculateCalories(pet)

        //Then
        println("result 210 is:$result")
        assert(result in 189.0..231.0)

    }


    /**
     * Calculate the calories needed for an `kitty` , that `weights 5kg`, has
     * a `high activity level` wants to `maintain weight` and is ` not sterilized`.
     * The result should being somewhere about 10% difference of 374:
     * ```
     * result in 336.6..411.4
     * ```
     */
    @Test
    fun `it should return a result about 10 percent difference from 374kcal`(){
        //Given
        val pet = PetModel(
            -1,
            "cat",
            "",
            0.9f,
            5.0f,
            null,
            false,
            "Alta",
            "Mantenimiento",
            null
        )

        //When
        val result = caloriesCalculator.calculateCalories(pet)

        //Then
        println("result 374 is:$result")
        assert(result in 336.6..411.4)

    }

    /**
     * Calculate the calories needed for an `senior` cat, that `weights 2kg`, has
     * a `medium activity level` wants to `maintain weight` and is ` not sterilized`.
     * The result should being somewhere about 10% difference of 112:
     * ```
     * result in 100.8..123.2
     * ```
     */
    @Test
    fun `it should return a result about 10 percent difference from 112kcal`(){
        //Given
        val pet = PetModel(
            -1,
            "cat",
            "",
            9.0f,
            2.0f,
            null,
            false,
            "Media",
            "Mantenimiento",
            null
        )

        //When
        val result = caloriesCalculator.calculateCalories(pet)

        //Then
        println("result 112 is:$result")
        assert(result in 100.8..123.2)

    }

}