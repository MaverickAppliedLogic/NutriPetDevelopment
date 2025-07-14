package com.maverickapps.nutripet.core.data.datastore

import com.google.gson.Gson
import java.io.File
import java.io.FileWriter

class VersionDatastore(private val updateFile: File) {

    private val gson = Gson()

    fun getUpdateState(): Double {
        val lastVersion: Double
        val json = updateFile.readText()
        lastVersion = gson.fromJson(json, Double::class.java) ?: 0.5
        return lastVersion
    }


    fun setUpdateState(currentVersion: Double) {
        val json = gson.toJson(currentVersion)
        println ("setupdate $currentVersion")
        FileWriter(updateFile).use { writer ->
            writer.write(json)
        }
    }

}