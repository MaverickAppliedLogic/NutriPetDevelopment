package com.maverickapps.nutripet.features.events.scheduler

import android.app.Activity
import android.app.AlarmManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getSystemService
import com.maverickapps.nutripet.features.events.scheduler.permissionChecker.NotificationPermissionChecker
import com.maverickapps.nutripet.features.events.scheduler.setter.MealNotificationSetter

class NotificationScheduler(context: Context) {

    private val thisContext = context
    private val alarmManager = getSystemService(thisContext, AlarmManager::class.java) as AlarmManager

    private val permissionChecker = NotificationPermissionChecker(thisContext, alarmManager)
    private val setter = MealNotificationSetter(thisContext, alarmManager)

    fun scheduleEvent(time: Long) {
        if (permissionChecker.isPostPermissionGranted()
            && permissionChecker.isSchedulePermissionGranted()) {
            Log.d("NotificationScheduler", "Scheduling event at $time")
            setter.setEvent(time)
        }
    }

    fun checkPostPermission(): Boolean{
      return permissionChecker.isPostPermissionGranted()
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun postPermissionRequest(activity: Activity){
        permissionChecker.requestPostPermission(activity)
    }

    fun checkSchedulePermission(): Boolean{
        return permissionChecker.isSchedulePermissionGranted()
    }

    @RequiresApi(Build.VERSION_CODES.S)
    fun schedulePermissionRequest(){
        permissionChecker.requestSchedulePermission()
    }
}

