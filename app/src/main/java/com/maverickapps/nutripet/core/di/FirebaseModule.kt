package com.maverickapps.nutripet.core.di

import com.maverickapps.nutripet.core.data.services.firebase.auth.AuthService
import com.maverickapps.nutripet.core.data.services.firebase.remoteConfig.RemoteConfigService
import com.maverickapps.nutripet.core.data.services.firebase.remoteConfig.implementations.RemoteConfigServiceLatestVerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    @Singleton
    fun provideRemoteConfigService(): RemoteConfigService {
        return RemoteConfigServiceLatestVerImpl()
    }

    @Provides
    @Singleton
    fun provideAuthService(): AuthService{
        return AuthService()
    }
}