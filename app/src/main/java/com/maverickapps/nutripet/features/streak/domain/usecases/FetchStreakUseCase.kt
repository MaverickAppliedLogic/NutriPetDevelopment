package com.maverickapps.nutripet.features.streak.domain.usecases

import java.util.Calendar
import javax.inject.Inject

class FetchStreakUseCase @Inject constructor(
    private val getStreakUseCase: GetStreakUseCase,
    private val updateStreakUseCase: UpdateStreakUseCase
) {
    operator fun invoke(){
        val today = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }.timeInMillis

        val streak = getStreakUseCase()

        val lastDateCalendar = Calendar.getInstance().apply {
            timeInMillis = streak.lastDate
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }.timeInMillis
        val daysBetween =
            ((today - lastDateCalendar) / (1000 * 60 * 60 * 24)).toInt()

        val updatedStreak = when {
            daysBetween > 1 ->
                streak.copy(lastDate = today, currentStreak = 0)
            daysBetween == 1 ->
                streak.copy(lastDate = today, currentStreak = streak.currentStreak + 1)
            else ->
                streak
        }

        updateStreakUseCase(updatedStreak)
    }
}