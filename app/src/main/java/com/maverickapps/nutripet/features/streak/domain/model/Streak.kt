package com.maverickapps.nutripet.features.streak.domain.model

data class Streak(
    val userId: String,
    val lastDate: Long,
    val currentDate: Long,
    val currentStreak: Int,
)

