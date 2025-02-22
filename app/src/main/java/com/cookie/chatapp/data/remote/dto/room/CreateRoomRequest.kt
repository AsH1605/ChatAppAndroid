package com.cookie.chatapp.data.remote.dto.room

import kotlinx.serialization.Serializable

@Serializable
data class CreateRoomRequest(
    val description: String
)
