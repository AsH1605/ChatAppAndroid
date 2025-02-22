package com.cookie.chatapp.domain.repository

import com.cookie.chatapp.domain.models.RoomModel

interface AllRoomRepository {

    suspend fun getAllRooms(): List<RoomModel>

    suspend fun deleteRoom(code: Int): Boolean

    suspend fun leaveRoom(code: Int): Boolean

    suspend fun createRoom(description: String): RoomModel?

    suspend fun joinRoom(code: Int): RoomModel?
}