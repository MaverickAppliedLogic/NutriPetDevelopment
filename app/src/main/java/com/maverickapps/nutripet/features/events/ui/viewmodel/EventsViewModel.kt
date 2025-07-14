package com.maverickapps.nutripet.features.events.ui.viewmodel

import android.app.Activity
import android.icu.util.Calendar
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.maverickapps.nutripet.features.events.domain.useCase.exactAlarmPermissions.CheckExactAlarmPermissionUseCase
import com.maverickapps.nutripet.features.events.domain.useCase.exactAlarmPermissions.RequestExactAlarmPermissionUseCase
import com.maverickapps.nutripet.features.events.domain.useCase.notificationsPermissions.CheckNotificationPermissionUseCase
import com.maverickapps.nutripet.features.events.domain.useCase.notificationsPermissions.RequesNotificationPermissionUseCase
import com.maverickapps.nutripet.features.events.domain.useCase.schedule.ScheduleDayChangerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class EventsViewModel @Inject constructor(
    private val checkExactAlarmPermissionUseCase: CheckExactAlarmPermissionUseCase,
    private val requestExactAlarmPermissionUseCase: RequestExactAlarmPermissionUseCase,
    private val checkNotificationPermissionUseCase: CheckNotificationPermissionUseCase,
    private val requestPostPermissionUseCase: RequesNotificationPermissionUseCase,
    private val scheduleDayChangerUseCase: ScheduleDayChangerUseCase
): ViewModel(){

    private val _mustRequestExactAlarmDialog = MutableStateFlow(true)
    val mustRequestExactAlarmDialog: StateFlow<Boolean> = _mustRequestExactAlarmDialog

    private val _mustRequestPostPermissionDialog = MutableStateFlow(true)
    val mustRequestPostPermissionDialog: StateFlow<Boolean> = _mustRequestPostPermissionDialog


    init {
        checkPermissions()
    }

    private fun checkPermissions(){
        _mustRequestPostPermissionDialog.value = !checkNotificationPermissionUseCase()
        _mustRequestExactAlarmDialog.value = !checkExactAlarmPermissionUseCase()
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun requestPostPermission(activity: Activity){
        requestPostPermissionUseCase(activity)
        checkPermissions()
    }

    @RequiresApi(Build.VERSION_CODES.S)
    fun requestExactAlarmPermission(){
        requestExactAlarmPermissionUseCase()
        checkPermissions()
    }

    fun scheduleDayChanger(){
        val time = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        if(time.timeInMillis < System.currentTimeMillis()){
            time.add(Calendar.DAY_OF_MONTH, 1)
        }
        scheduleDayChangerUseCase(time.timeInMillis)
    }

}