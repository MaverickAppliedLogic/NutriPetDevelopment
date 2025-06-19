package com.maverickapps.nutripet.petsFeature.domain.otherTasks.useCase

import com.maverickapps.nutripet.core.data.UpdatesRepository
import javax.inject.Inject

class SetUpdateStateUseCase @Inject constructor(private val updatesRepository: UpdatesRepository){
    operator fun invoke(isUpdated: Boolean) {
        updatesRepository.setUpdateState(isUpdated)
    }
}