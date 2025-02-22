package com.cookie.chatapp.data.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.cookie.chatapp.domain.manager.PreferenceManager
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

val Context.settingsDataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class PreferencesManagerImpl(context: Context): PreferenceManager {

    private val preferences = context.settingsDataStore

    override suspend fun getLoggedInUserId(): String? {
        return preferences.data.map { prefs->
            prefs[PreferenceKeys.loggedInUserKey]
        }.first()
    }

    override suspend fun setLoggedInWorker(id: String?) {
        preferences.edit { prefs->
            if(id == null){
                prefs.remove(PreferenceKeys.loggedInUserKey)
            }
            else{
                prefs[PreferenceKeys.loggedInUserKey] = id
            }
        }
    }
}