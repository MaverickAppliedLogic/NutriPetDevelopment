package com.maverickapps.nutripet.features.events.domain.dayChangerEvents.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.maverickapps.nutripet.features.notifications.domain.useCase.RefreshScheduleNotificationsUseCase
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DayChanger  : BroadcastReceiver() {

    @Inject lateinit var refreshScheduleNotificationsUseCase: RefreshScheduleNotificationsUseCase

    companion object {
        const val DAY_CHANGE_EVENT_ID = 3001
    }
    override fun onReceive(context: Context, intent: Intent) {
        refreshScheduleNotificationsUseCase()
    }
}