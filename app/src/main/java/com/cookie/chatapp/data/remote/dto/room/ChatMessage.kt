package com.cookie.chatapp.data.remote.dto.room

import kotlinx.serialization.Serializable

@Serializable
data class ChatMessage(
    val userRoom: UserRoom,
    val content: String
)
