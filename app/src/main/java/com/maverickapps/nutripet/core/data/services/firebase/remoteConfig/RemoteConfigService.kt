package com.maverickapps.nutripet.core.data.services.firebase.remoteConfig

interface RemoteConfigService {
    suspend fun fetchValues(): Boolean
    fun getLatestVersion(): Double
}