package com.maverickapps.nutripet.features.pets.domain.objectTasks.petFood.useCase

import com.maverickapps.nutripet.features.pets.data.repositories.FoodRepository
import com.maverickapps.nutripet.features.pets.data.repositories.PetFoodRepository
import com.maverickapps.nutripet.features.pets.domain.objectTasks.food.model.FoodModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class GetFoodsByPetIdUseCase @Inject constructor(
    private val petFoodRepository: PetFoodRepository,
    private val foodRepository: FoodRepository
) {

    suspend operator fun invoke(petId: Int): List<FoodModel> = withContext(Dispatchers.IO) {
            val foodsId = petFoodRepository.getFoodsIdByPetId(petId)
            return@withContext if(foodsId.isEmpty()){ emptyList()}
            else{
                if(foodsId.size > 3) getFoods(foodsId)
                else{
                    val chunkSize = maxOf(1,foodsId.size / 4)
                    val chunkedIds = foodsId.chunked(chunkSize)

                    val deferredResults = chunkedIds.map { subList ->
                        async { getFoods(subList) }
                    }

                    deferredResults.flatMap { it.await() }
                }
            }



    }

    private suspend fun getFoods(foodIds: List<Int>): List<FoodModel> {
        return foodIds.map { foodRepository.getFoodById(it) }
    }
}