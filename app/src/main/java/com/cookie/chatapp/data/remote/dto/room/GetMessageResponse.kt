package com.cookie.chatapp.data.remote.dto.room

import com.cookie.chatapp.data.remote.dto.user.User
import kotlinx.serialization.Serializable

@Serializable
data class GetMessageResponse(
    val status: String,
    val data: MessageList
)

@Serializable
data class MessageList(
    val messages: List<Message>
)

@Serializable
data class Message(
    val content: String,
    val status: String,
    val isSystem: Boolean,
    val user: User,
    val roomCode: String
)