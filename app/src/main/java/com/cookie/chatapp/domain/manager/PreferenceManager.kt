package com.cookie.chatapp.domain.manager

interface PreferenceManager {
    suspend fun getLoggedInUserId(): Int?

    suspend fun setLoggedInWorker(id: Int?)
}