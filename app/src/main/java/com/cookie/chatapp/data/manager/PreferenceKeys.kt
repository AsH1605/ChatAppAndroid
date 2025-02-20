package com.cookie.chatapp.data.manager

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PreferenceKeys {
    val loggedInUserKey: Preferences.Key<String> = stringPreferencesKey("logged_in_user_id")
}