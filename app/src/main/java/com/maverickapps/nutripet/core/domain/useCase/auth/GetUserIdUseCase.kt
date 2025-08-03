package com.maverickapps.nutripet.core.domain.useCase.auth

import com.maverickapps.nutripet.core.data.repositories.AuthRepository
import javax.inject.Inject

class GetUserIdUseCase @Inject constructor(
    private val authRepository: AuthRepository
){
    operator fun invoke(): String = authRepository.getUid() ?: "No User"
}