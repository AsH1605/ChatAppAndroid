package com.cookie.chatapp.data.remote.dto.user

import kotlinx.serialization.Serializable

@Serializable
data class UserLoginRequest(
    val username: String,
    val password: String
)
