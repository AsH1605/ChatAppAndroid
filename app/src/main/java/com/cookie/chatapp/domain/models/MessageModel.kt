package com.cookie.chatapp.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class MessageModel (
    val content: String,
    val status: String,
    val isSystem: Boolean,
    val userId: String,
    val roomCode: String,
    val username: String
)