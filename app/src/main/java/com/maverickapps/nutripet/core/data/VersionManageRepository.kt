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
        val fetched = remoteConfigService.fetchValues()
        println(fetched)
        val newVersion = remoteConfigService.getLatestVersion()
        println("newVersion: $newVersion")
        versionLocalStorage.setUpdateState(newVersion)

    }

    suspend fun getLatestVersion(): Double {
        val fetched = remoteConfigService.fetchValues()
        println(fetched)
        return remoteConfigService.getLatestVersion()
    }
}