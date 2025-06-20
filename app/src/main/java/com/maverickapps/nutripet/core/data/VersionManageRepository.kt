package com.maverickapps.nutripet.core.data

import com.maverickapps.nutripet.core.data.localStorage.VersionLocalStorage
import com.maverickapps.nutripet.core.data.services.firebase.remoteConfig.RemoteConfigService
import javax.inject.Inject

class VersionManageRepository @Inject constructor(
    private val versionLocalStorage: VersionLocalStorage,
    private val remoteConfigService: RemoteConfigService
) {

    fun getVersionLocalStorage(): Double{
        return versionLocalStorage.getUpdateState()
    }

    suspend fun fetchVersionLocalStorage(){
        remoteConfigService.fetchValues()
        val newVersion = remoteConfigService.getLatestVersion()
        versionLocalStorage.setUpdateState(newVersion)
    }

    suspend fun getLatestVersion(): Double {
        remoteConfigService.fetchValues()
        return remoteConfigService.getLatestVersion()
    }

}