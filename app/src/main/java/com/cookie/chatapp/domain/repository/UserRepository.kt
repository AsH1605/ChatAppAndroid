package com.cookie.chatapp.domain.repository

interface UserRepository {

    suspend fun loginUser()
}