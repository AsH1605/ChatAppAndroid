package com.cookie.chatapp.domain.repository

import com.cookie.chatapp.domain.models.MessageModel
import com.cookie.chatapp.domain.models.RoomEvent
import kotlinx.coroutines.flow.Flow

interface RoomRepository {
    suspend fun sendMessage(roomCode: String, username: String, message: String)

    suspend fun joinRoom(roomCode: String, userId: String)

    suspend fun connect()

    fun getRoomEvents(): Flow<RoomEvent>

    suspend fun disconnect()

    suspend fun getAllMessages(roomCode: String): List<MessageModel>
}