package com.cookie.chatapp.data.remote

import com.cookie.chatapp.data.remote.dto.room.GetAllRoomResponse

interface AllRoomApi {

    suspend fun createRoom(userId: Int)

    suspend fun joinRoom(userId: Int)

    suspend fun deleteRoom(userId: Int)

    suspend fun leaveRoom(userId: Int)

    suspend fun getAllRoom(userId: Int): GetAllRoomResponse
}