package com.maverickapps.nutripet.features.pets.domain.objectTasks.meal.useCase

import com.maverickapps.nutripet.features.notifications.domain.useCase.DeleteNotificationUseCase
import com.maverickapps.nutripet.features.notifications.domain.useCase.RefreshScheduleNotificationsUseCase
import com.maverickapps.nutripet.features.pets.data.repositories.MealsRepository
import javax.inject.Inject

class DeleteMealUseCase @Inject constructor(
    private val mealsRepository: MealsRepository,
    private val deleteNotificationUseCase: DeleteNotificationUseCase,
    private val refreshScheduleNotificationsUseCase: RefreshScheduleNotificationsUseCase
){
    suspend operator fun invoke(mealId: Int){
        mealsRepository.deleteMealForAPet(mealId)
        deleteNotificationUseCase(mealId.hashCode())
        refreshScheduleNotificationsUseCase()
    }
}