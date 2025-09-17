package com.maverickapps.nutripet.core.data.uniqueRepositories

import com.maverickapps.nutripet.core.data.datastore.VersionDatastore
import com.maverickapps.nutripet.core.services.firebase.remoteConfig.RemoteConfigService
import javax.inject.Inject

class VersionManageRepository @Inject constructor(
    private val versionDatastore: VersionDatastore,
    private val remoteConfigService: RemoteConfigService
) {

    fun getVersionLocalStorage(): Double{
        return versionDatastore.getUpdateState()
    }


    suspend fun fetchVersionLocalStorage(){
        val fetched = remoteConfigService.fetchValues()
        println(fetched)
        val newVersion = remoteConfigService.getLatestVersion()
        println("newVersion: $newVersion")
        versionDatastore.setUpdateState(newVersion)

    }

    suspend fun getLatestVersion(): Double {
        val fetched = remoteConfigService.fetchValues()
        println(fetched)
        return remoteConfigService.getLatestVersion()
    }
}