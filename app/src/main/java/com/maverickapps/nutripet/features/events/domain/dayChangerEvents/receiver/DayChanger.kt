package com.maverickapps.nutripet.features.events.domain.dayChangerEvents.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.maverickapps.nutripet.features.events.domain.useCase.schedule.ScheduleDayChangerUseCase
import com.maverickapps.nutripet.features.notifications.domain.useCase.RefreshScheduleNotificationsUseCase
import com.maverickapps.nutripet.features.pets.domain.objectTasks.meal.useCase.UpdateMealsDayChangedUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DayChanger  : BroadcastReceiver() {

    @Inject lateinit var refreshScheduleNotificationsUseCase: RefreshScheduleNotificationsUseCase
    @Inject lateinit var updateMealsDayChangedUseCase: UpdateMealsDayChangedUseCase
    @Inject lateinit var scheduleDayChangerUseCase: ScheduleDayChangerUseCase

    companion object {
        const val DAY_CHANGE_EVENT_ID = 3001
    }
    override fun onReceive(context: Context, intent: Intent) {
        CoroutineScope(Dispatchers.IO).launch {
            Log.d("DayChanger", "Day changed")
            updateMealsDayChangedUseCase()
            refreshScheduleNotificationsUseCase()
            scheduleDayChangerUseCase()
        }
    }
}