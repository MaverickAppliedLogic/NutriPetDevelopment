package com.maverickapps.nutripet.petsFeature.domain.otherTasks.useCase

import com.maverickapps.nutripet.core.data.UpdatesRepository
import javax.inject.Inject

class GetUpdateStateUseCase @Inject constructor(private val updatesRepository: UpdatesRepository){
    operator fun invoke(): Boolean{
        return updatesRepository.getUpdateState()
    }
}