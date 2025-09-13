package com.maverickapps.nutripet.features.events.domain.useCase.streak

import com.maverickapps.nutripet.features.streak.domain.usecases.GetStreakUseCase
import com.maverickapps.nutripet.features.streak.domain.usecases.UpdateStreakUseCase
import javax.inject.Inject

class ResetStreakNewDayUseCase @Inject constructor(
    private val getStreakUseCase: GetStreakUseCase,
    private val updateStreakUseCase: UpdateStreakUseCase
) {
    operator fun invoke(){
        val streak = getStreakUseCase()
        updateStreakUseCase(streak.copy(alreadyFetched = false))
    }
}