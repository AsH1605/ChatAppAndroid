package com.cookie.chatapp.data.repository

import com.cookie.chatapp.data.remote.AllRoomApi
import com.cookie.chatapp.domain.manager.PreferenceManager
import com.cookie.chatapp.domain.models.RoomModel
import com.cookie.chatapp.domain.repository.AllRoomRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class RoomRepositoryImpl(
    private val allRoomApi: AllRoomApi,
    private val preferenceManager: PreferenceManager,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): AllRoomRepository{
    override suspend fun getAllRooms(code: Int): List<RoomModel> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteRoom(code: Int): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun leaveRoom(code: Int): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun createRoom(description: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun joinRoom(code: Int): Boolean {
        TODO("Not yet implemented")
    }

}