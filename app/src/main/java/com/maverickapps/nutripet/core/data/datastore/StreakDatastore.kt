package com.maverickapps.nutripet.core.data.datastore

import com.google.gson.Gson
import com.maverickapps.nutripet.features.dataSync.streak.contract.StreakLocalSource
import com.maverickapps.nutripet.features.streak.domain.model.Streak
import java.io.File

class StreakDatastore(private val streakFile: File): StreakLocalSource {

    private val gson = Gson()

   override fun getStreak(): Streak {
        val json = streakFile.readText()
        return gson.fromJson(json, Streak::class.java)?: Streak(
            "", 0, 0,false)
    }

   override fun setStreak(streak: Streak) {
        val json = gson.toJson(streak)
        streakFile.writeText(json)
    }
}