package com.cookie.chatapp.data.manager

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey

object PreferenceKeys {
    val loggedInUserKey: Preferences.Key<Int> = intPreferencesKey("logged_in_user")
}