package com.loc.newsapp.ft_onboarding.data.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.loc.newsapp.ft_onboarding.domain.manager.LocalUserManager
import com.loc.newsapp.util.constants.IS_ONBOARDING_COMPLETE
import com.loc.newsapp.util.constants.USER_SETTINGS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalUserManagerImpl(private val context: Context) : LocalUserManager {
    override suspend fun saveAppEntry() {
        context.dataStore.edit { userSettings ->
            userSettings[PreferencesKeys.IsComplete] = true
        }
    }

    override fun readAppEntry(): Flow<Boolean> {
        return  context.dataStore.data.map { preferences ->
            preferences[PreferencesKeys.IsComplete] ?: false
        }
    }
}

private val Context.dataStore:DataStore<Preferences> by preferencesDataStore(
    name = USER_SETTINGS

)

private object PreferencesKeys{
    val IsComplete = booleanPreferencesKey( IS_ONBOARDING_COMPLETE)
}
