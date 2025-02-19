package com.cookie.chatapp.data.remote.dto.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserLoginResponse(
    val success: Boolean,
    @SerialName("authorization") val idToken: String,
    val data: UserDataResponse
)

@Serializable
data class UserDataResponse(
    @SerialName("userDetails") val user: User
)


@Serializable
data class User(
    @SerialName("_id") val id: String,
    val username: String,
    val firstName: String,
    val lastName: String,
    val email: String
)