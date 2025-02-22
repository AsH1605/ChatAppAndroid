package com.cookie.chatapp.data.remote.dto.allRooms

import kotlinx.serialization.Serializable

@Serializable
data class CreateRoomRequest(
    val description: String
)
