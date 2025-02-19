package com.cookie.chatapp.data.remote.dto.user

import kotlinx.serialization.Serializable

@Serializable
data class UserRegisterRequest(
    val username: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
)
