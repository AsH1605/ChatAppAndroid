package com.cookie.chatapp.presentation.room.model

import com.cookie.chatapp.domain.models.MessageModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

data class UiState(
    val roomCode: String,
    val message: String,
    val allMessages: List<MessageModel>,
    val userId: String
){
    val lastActivity = allMessages.lastOrNull()?.updatedAt?:""
}
