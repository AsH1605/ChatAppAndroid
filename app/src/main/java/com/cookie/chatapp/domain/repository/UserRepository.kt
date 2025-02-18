package com.cookie.chatapp.domain.repository

import com.cookie.chatapp.domain.models.UserModel

interface UserRepository {

    suspend fun registerUser(user: UserModel): Boolean

    suspend fun loginUser()
}