package com.example.feedm.petsFeature.domain.objectTasks.meal.useCase

import com.example.feedm.petsFeature.domain.objectTasks.meal.model.MealModel
import com.example.feedm.petsFeature.data.MealsRepository
import com.example.feedm.petsFeature.domain.objectTasks.food.model.FoodModel
import com.example.feedm.petsFeature.domain.objectTasks.food.useCase.AddFoodUseCase
import com.example.feedm.petsFeature.utils.MealModelBuilder
import javax.inject.Inject

class AddMealUseCase @Inject constructor(
    private val addFoodUseCase: AddFoodUseCase,
    private val mealsRepository: MealsRepository,
    private val mealModelBuilder: MealModelBuilder) {
    suspend operator fun invoke(mealModel: MealModel?,
                                petId: Int?,
                                food: FoodModel,
                                ration: Float?,
                                hour: Int?,
                                min: Int?,
                                mealCalories: Double?){

            val foodId = if(food.foodId == 0) addFoodUseCase(food, petId).toInt() else food.foodId

            val mealModelBuilt = mealModel ?:
            mealModelBuilder(petId!!, foodId, ration!!, hour!!, min!!, mealCalories!!)

            mealsRepository.addMealForAPet(mealModelBuilt)

    }
}