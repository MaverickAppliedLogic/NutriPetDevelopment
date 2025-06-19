package com.maverickapps.nutripet.core.data

import com.maverickapps.nutripet.core.data.local.UpdatesNotifier
import javax.inject.Inject

class UpdatesRepository @Inject constructor(private val updatesNotifier: UpdatesNotifier) {

    fun getUpdateState(): Boolean{
       return updatesNotifier.getUpdateState()
    }

    fun setUpdateState(isUpdated: Boolean){
        updatesNotifier.setUpdateState(isUpdated)
    }
}