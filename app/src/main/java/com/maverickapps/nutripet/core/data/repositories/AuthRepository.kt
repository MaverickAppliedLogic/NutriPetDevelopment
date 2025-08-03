package com.maverickapps.nutripet.core.data.repositories

import com.maverickapps.nutripet.core.data.services.firebase.auth.AuthService
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authService: AuthService,
){
    suspend fun signInAnonymously(): String{
        return authService.signInAnonymously().getOrNull()?: "e"
    }

    fun getUid() : String? {
       return authService.getUid()
    }
}