package com.maverickapps.nutripet.features.streak.domain.model

import com.google.firebase.firestore.DocumentSnapshot

data class Streak(
    val userId: String,
    val startDate: Long,
    val endDate: Long,
    val currentStreak: Int,
)

fun DocumentSnapshot.toStreak() =
    Streak(userId = get("userId") as String,
        startDate = get("startDate") as Long,
        endDate = get("endDate") as Long,
        currentStreak = get("currentStreak") as Int)