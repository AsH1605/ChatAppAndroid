package com.cookie.chatapp.data.remote.dto.room

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnyRoomRequest(
    @SerialName("roomCode") val code: String
)
