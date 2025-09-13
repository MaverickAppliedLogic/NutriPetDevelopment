package com.maverickapps.nutripet.core.domain.useCase.auth

import com.maverickapps.nutripet.core.data.uniqueRepositories.AuthRepository
import javax.inject.Inject

class SignInAnonymouslyUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(): String = authRepository.signInAnonymously()
}