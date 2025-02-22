package com.cookie.chatapp.data.remote.dto.room

import kotlinx.serialization.Serializable

@Serializable
data class DeleteRoomResponse(
    val status: String,
    val error: String?
)


