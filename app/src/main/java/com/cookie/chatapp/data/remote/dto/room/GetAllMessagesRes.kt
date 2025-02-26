package com.cookie.chatapp.data.remote.dto.room

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetAllMessagesRes(
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
    val user: MessageUser,
    val roomCode: String,
    val createdAt: String,
    val updatedAt: String
)

@Serializable
data class GetAllMessagesReq(
    val roomCode: String
)

@Serializable
data class MessageUser(
    @SerialName("_id") val id: String,
    val username: String,
    val firstName: String,
    val lastName: String,
)