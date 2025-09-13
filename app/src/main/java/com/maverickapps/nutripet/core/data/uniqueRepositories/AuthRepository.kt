package com.maverickapps.nutripet.core.data.uniqueRepositories

import com.maverickapps.nutripet.core.services.firebase.auth.AuthService
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authService: AuthService,
){
    suspend fun signInAnonymously(): String{
        return authService.signInAnonymously().toString()
    }

    fun getUid() : String? {
       return authService.getUid()
    }
}