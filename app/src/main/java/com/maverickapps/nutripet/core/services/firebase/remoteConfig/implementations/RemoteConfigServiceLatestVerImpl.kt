package com.maverickapps.nutripet.core.services.firebase.remoteConfig.implementations

import com.google.firebase.Firebase
import com.google.firebase.remoteconfig.remoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings
import com.maverickapps.nutripet.core.services.firebase.remoteConfig.RemoteConfigService
import kotlinx.coroutines.tasks.await

class RemoteConfigServiceLatestVerImpl : RemoteConfigService {

    private val remoteConfig = Firebase.remoteConfig

    init{
        remoteConfig.setConfigSettingsAsync(
            remoteConfigSettings {
                minimumFetchIntervalInSeconds = 0
            }
        )
    }

    override suspend fun fetchValues(): Boolean {
        return remoteConfig.fetchAndActivate().await()
    }

    override fun getLatestVersion(): Double {
        return remoteConfig.getDouble("latestVersion")
    }
}