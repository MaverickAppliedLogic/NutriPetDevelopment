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
        println("result is:$result")
        assert(result in 888.3..1085.7)

    }

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
        println("result is:$result")
        assert(result in 446.4..545.6)

    }
}