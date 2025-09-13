package com.maverickapps.nutripet.core.data.firestore.streak

import com.maverickapps.nutripet.core.domain.toFirestoreStreak
import com.maverickapps.nutripet.core.domain.toStreak
import com.maverickapps.nutripet.core.services.firebase.firestore.FirestoreService
import com.maverickapps.nutripet.features.streak.contract.StreakRemoteRepository
import com.maverickapps.nutripet.features.streak.domain.model.Streak
import javax.inject.Inject

class FirestoreStreakRepository : StreakRemoteRepository{

    @Inject lateinit var firestoreService: FirestoreService

    override suspend fun getStreak(userId: String): Streak {
        firestoreService = FirestoreService()
        return firestoreService.getCollection("streak").result.documents
            .first { it.get("userId") == userId }.toStreak()
    }

    override suspend fun setStreak(streak: Streak) {
        firestoreService = FirestoreService()
        firestoreService.setDocument("streak",
            documentId = streak.userId,streak.toFirestoreStreak())
    }
}

