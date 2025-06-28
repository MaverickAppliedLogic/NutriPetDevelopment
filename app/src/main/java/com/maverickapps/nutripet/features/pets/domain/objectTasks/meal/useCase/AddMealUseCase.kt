package com.maverickapps.nutripet.features.pets.domain.objectTasks.meal.useCase

import com.maverickapps.nutripet.features.events.contract.EventSetter
import com.maverickapps.nutripet.features.pets.data.MealsRepository
import com.maverickapps.nutripet.features.pets.domain.objectTasks.meal.model.MealModel
import javax.inject.Inject

class AddMealUseCase @Inject constructor(
    private val mealsRepository: MealsRepository,
    private val mealNotificationSetter: EventSetter
) {
    suspend operator fun invoke(mealModel: MealModel){
        mealsRepository.addMealForAPet(mealModel)
        mealNotificationSetter.setEvent(mealModel.mealTime)
    }
}