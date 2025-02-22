package com.cookie.chatapp.data.remote.dto.allRooms

import kotlinx.serialization.Serializable

@Serializable
data class DeleteRoomResponse(
    val status: String,
    val error: String?
)


