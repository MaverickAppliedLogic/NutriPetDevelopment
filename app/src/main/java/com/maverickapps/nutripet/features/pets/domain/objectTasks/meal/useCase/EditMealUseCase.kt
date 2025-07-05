package com.maverickapps.nutripet.features.pets.domain.objectTasks.meal.useCase

import com.maverickapps.nutripet.features.events.domain.useCase.schedule.ScheduleNotificationUseCase
import com.maverickapps.nutripet.features.notifications.domain.model.toMealNotificationModel
import com.maverickapps.nutripet.features.notifications.domain.useCase.EditNotificationUseCase
import com.maverickapps.nutripet.features.pets.data.repositories.MealsRepository
import com.maverickapps.nutripet.features.pets.domain.objectTasks.meal.model.MealModel
import javax.inject.Inject

class EditMealUseCase @Inject constructor(
    private val mealRepository: MealsRepository,
    private val editNotificationUseCase: EditNotificationUseCase,
    private val scheduleNotificationUseCase: ScheduleNotificationUseCase
) {

    suspend operator fun invoke(meal: MealModel) {
        mealRepository.editMeal(meal)
        if (meal.isDailyMeal) {
            editNotificationUseCase(meal.toMealNotificationModel())
        }
        else{
            scheduleNotificationUseCase(
                meal.mealTime,
                meal.mealId,
                meal.petId.toString(),
                false
            )
        }
    }

}