package com.cookie.chatapp.data.remote.dto.room

import kotlinx.serialization.Serializable

@Serializable
data class UserRoom(
    val name: String,
    val room: String
)