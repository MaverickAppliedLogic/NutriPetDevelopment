package com.maverickapps.nutripet.core.data.firestore.streak

import com.maverickapps.nutripet.core.data.services.firebase.firestore.FirestoreService
import com.maverickapps.nutripet.features.streak.contract.StreakRemoteDataSource
import com.maverickapps.nutripet.features.streak.domain.model.Streak
import com.maverickapps.nutripet.features.streak.domain.model.toStreak
import javax.inject.Inject

class FirestoreStreakDataSource : StreakRemoteDataSource {

    @Inject lateinit var firestoreService : FirestoreService

    override suspend fun getStreak(userId: String): Streak {
        return firestoreService.getCollection("streak").result.documents
            .first { it.get("userId") == userId }.toStreak()
    }

    override suspend fun setStreak(userId: String, streak: Streak) {
        val normalizedStreak = hashMapOf(
            "userId" to userId,
            "startDate" to streak.startDate,
            "endDate" to streak.endDate,
            "currentStreak" to streak.currentStreak
        )
        firestoreService.createDocument("streak", normalizedStreak)
    }
}