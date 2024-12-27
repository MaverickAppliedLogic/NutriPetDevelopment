package com.example.feedm.petMealsFeature.domain.model

import com.example.feedm.core.domain.model.MealModel
import com.example.feedm.core.domain.model.PetModel
import com.example.feedm.petMealsFeature.data.PetMealsRepository

data class PetMealsModel(
    var pet: PetModel =
        PetModel(
            0,
            0,
            "",
            "",
            0.0f,
            0.0f,
            "",
            false,
            "",
            "",
            ""
        ),
    var meals: List<MealModel> = emptyList()
)


