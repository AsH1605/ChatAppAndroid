package com.cookie.chatapp.presentation.room.model

import com.cookie.chatapp.domain.models.MessageModel

data class UiState(
    val roomCode: String,
    val message: String,
    val allMessages: List<MessageModel>,
    val userId: String
)
