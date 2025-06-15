package com.maverickapps.nutripet.core.domain.useCase

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