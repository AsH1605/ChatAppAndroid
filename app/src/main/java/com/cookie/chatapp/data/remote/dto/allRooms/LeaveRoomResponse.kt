package com.cookie.chatapp.data.remote.dto.allRooms

import kotlinx.serialization.Serializable

@Serializable
data class LeaveRoomResponse(
    val status: String,
    val error: String?
)