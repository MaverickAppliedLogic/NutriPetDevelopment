package com.maverickapps.nutripet.features.events.domain.useCase

import com.maverickapps.nutripet.core.utils.DayHandler
import javax.inject.Inject

class CheckDayChangedUseCase @Inject constructor(
    private val dayHandler: DayHandler
) {
    suspend operator fun invoke(): Boolean {
      if(dayHandler.hasDayChanged()){
          dayHandler.fetchDay()
          return true
      }
      else return false
    }
}