package com.maverickapps.nutripet.core.domain

import com.google.firebase.firestore.DocumentSnapshot
import com.maverickapps.nutripet.features.streak.domain.model.Streak

//Streak <--> FireStoreStreak
fun Streak.toFirestoreStreak() =
    hashMapOf(
        "lastDate" to lastDate,
        "currentDate" to currentDate,
        "currentStreak" to currentStreak
    )
fun DocumentSnapshot.toStreak() =
    Streak(userId = id,
        lastDate = get("lastDate") as Long,
        currentDate = get("currentDate") as Long,
        currentStreak = get("currentStreak") as Int)