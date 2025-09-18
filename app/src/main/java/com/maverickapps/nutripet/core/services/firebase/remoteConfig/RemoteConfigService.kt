package com.maverickapps.nutripet.core.services.firebase.remoteConfig

interface RemoteConfigService {
    suspend fun fetchValues(): Boolean
    suspend fun getLatestVersion(): Double
}