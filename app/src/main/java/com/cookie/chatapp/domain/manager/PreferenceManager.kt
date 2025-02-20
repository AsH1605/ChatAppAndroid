package com.cookie.chatapp.domain.manager

interface PreferenceManager {
    suspend fun getLoggedInUserId(): String?

    suspend fun setLoggedInWorker(id: String?)
}