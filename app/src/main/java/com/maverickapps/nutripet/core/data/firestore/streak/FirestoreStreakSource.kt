package com.maverickapps.nutripet.core.data.firestore.streak

import com.maverickapps.nutripet.core.domain.toFirestoreStreak
import com.maverickapps.nutripet.core.domain.toStreak
import com.maverickapps.nutripet.core.services.firebase.firestore.FirestoreService
import com.maverickapps.nutripet.features.dataSync.streak.contract.StreakRemoteSource
import com.maverickapps.nutripet.features.streak.domain.model.Streak
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirestoreStreakSource : StreakRemoteSource {

    @Inject lateinit var firestoreService: FirestoreService

    override suspend fun getStreak(userId: String): Streak {
       firestoreService = FirestoreService()
        val streakCollection = firestoreService.getCollection("streak").await()
        val streak = streakCollection.documents.runCatching { first { it.get("userId") == userId } }

        return if (streak.isSuccess) { //Check if the operation was successful
            streak.getOrNull()?.toStreak()?: //Check if the document exists
            Streak("No such document", //Else returns an empty Streak giving reason
                0,
                0,
                false)

        }
        else{
            Streak("Failed data retrieving", //If operation failed returns an empty
                0,                          // Streak giving reason
                0,
                false)
        }
        }

    override suspend fun setStreak(streak: Streak) {
        firestoreService = FirestoreService()
        val documentName = streak.userId.substring(8,streak.userId.length-1)
        firestoreService.setDocument("streak",
            documentId = documentName,streak.toFirestoreStreak())
    }
}

