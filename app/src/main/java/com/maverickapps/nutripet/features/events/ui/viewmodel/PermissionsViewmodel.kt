package com.maverickapps.nutripet.features.events.ui.viewmodel

import android.app.Activity
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.maverickapps.nutripet.features.events.scheduler.NotificationScheduler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class PermissionsViewmodel @Inject constructor(
    private val notificationScheduler: NotificationScheduler,
): ViewModel(){

    private val _mustRequestScheduleDialog = MutableStateFlow(true)

    val mustRequestScheduleDialog: StateFlow<Boolean> = _mustRequestScheduleDialog

    init {
        schedulePermissionIsGranted()
    }
    fun postPermissionIsGranted(): Boolean{
        return notificationScheduler.checkPostPermission()
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun requestPostPermission(activity: Activity){
        notificationScheduler.postPermissionRequest(activity)
    }

    fun schedulePermissionIsGranted(){
        _mustRequestScheduleDialog.value = !notificationScheduler.checkSchedulePermission()
    }

    @RequiresApi(Build.VERSION_CODES.S)
    fun schedulePermissionRequest(){
        notificationScheduler.schedulePermissionRequest()
        schedulePermissionIsGranted()
    }

}