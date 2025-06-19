package com.maverickapps.nutripet.core.data.local

import com.google.gson.Gson
import java.io.File
import java.io.FileWriter

class UpdatesNotifier(private val updateFile: File) {

    private val gson = Gson()

    fun getUpdateState(): Boolean{
        val isUpdated: Boolean
        try {
            val json = updateFile.readText()
            isUpdated = gson.fromJson(json, Boolean::class.java)
        } catch (_: NullPointerException) {
            return false
        }
        return isUpdated
    }

    fun setUpdateState(isUpdated: Boolean) {
        val json = gson.toJson(isUpdated)
        FileWriter(updateFile).use { writer ->
            writer.write(json)
        }
    }
}