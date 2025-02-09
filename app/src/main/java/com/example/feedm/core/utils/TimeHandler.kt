package com.example.feedm.core.utils


import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.first
import android.icu.util.Calendar
import androidx.datastore.preferences.core.edit
import javax.inject.Inject


class DayHandler @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    private val calendar: Calendar
)
{

    private val LAST_DAY_KEY = intPreferencesKey("lastDay")


    suspend fun hasDayChanged() : Boolean{
        val currentDay = calendar.get(Calendar.DAY_OF_YEAR)
        return currentDay != getLastDay()
    }

    suspend fun fetchDay(){
        val currentDay = calendar.get(Calendar.DAY_OF_YEAR)
        dataStore.edit { preferences ->
            preferences[LAST_DAY_KEY] = currentDay
        }
    }

    suspend fun getLastDay(): Int? {
        return dataStore.data.first()[LAST_DAY_KEY]

    }
}