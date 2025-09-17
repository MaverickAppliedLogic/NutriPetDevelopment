package com.maverickapps.nutripet.core.data.datastore

import com.google.gson.Gson
import java.io.File

class VersionDatastore(private val updateFile: File) {

    private val gson = Gson()

    fun getUpdateState(): Double {
        val json = updateFile.readText()
        return gson.fromJson(json, Double::class.java) ?: 0.5
    }


    fun setUpdateState(currentVersion: Double) {
        val json = gson.toJson(currentVersion)
        println ("setupdate $currentVersion")
        updateFile.writeText(json)
    }

}