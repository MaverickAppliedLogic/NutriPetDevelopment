package com.maverickapps.nutripet.features.streak.domain.model

data class Streak(
    val userId: String,
    val lastDate: Long,
    val currentStreak: Int,
    val alreadyFetched: Boolean
)


