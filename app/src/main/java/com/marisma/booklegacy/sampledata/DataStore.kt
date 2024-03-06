package com.marisma.booklegacy.sampledata


import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("settings")

object DataStoreUtil {
    private val LAST_USER_KEY = stringPreferencesKey("last_user")

    suspend fun saveLastUser(context: Context, user: String) {
        context.dataStore.edit { preferences ->
            preferences[LAST_USER_KEY] = user
        }
    }

    fun getLastUser(context: Context): Flow<String?> {
        return context.dataStore.data.map { preferences ->
            preferences[LAST_USER_KEY]
        }
    }
}