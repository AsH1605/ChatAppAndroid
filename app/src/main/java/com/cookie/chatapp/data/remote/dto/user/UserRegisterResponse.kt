package com.cookie.chatapp.data.remote.dto.user

import kotlinx.serialization.Serializable

@Serializable
data class UserRegisterResponse(
    val success: Boolean
)
