package com.cookie.chatapp.data.repository

import android.util.Log
import com.cookie.chatapp.data.local.dao.UserDao
import com.cookie.chatapp.data.mapper.toRoom
import com.cookie.chatapp.data.mapper.toRoomModel
import com.cookie.chatapp.data.remote.AllRoomApi
import com.cookie.chatapp.data.remote.dto.room.AnyRoomRequest
import com.cookie.chatapp.data.remote.dto.room.CreateRoomRequest
import com.cookie.chatapp.domain.manager.PreferenceManager
import com.cookie.chatapp.domain.models.RoomModel
import com.cookie.chatapp.domain.repository.AllRoomRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class AllRoomRepositoryImpl(
    private val allRoomApi: AllRoomApi,
    private val userDao: UserDao,
    private val preferenceManager: PreferenceManager,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): AllRoomRepository{
    override suspend fun getAllRooms(): List<RoomModel> = withContext(ioDispatcher) {
        try {
            val userId = preferenceManager.getLoggedInUserId()
            val idToken = userId?.let { userDao.getIdToken(it) }
            val rooms = idToken?.let { allRoomApi.getAllRoom(it) }
//            val rooms = if (idToken != null){
//                allRoomApi.getAllRoom(idToken)
//            }
//            else{
//                null
//            }
            rooms?.toRoom(userId.toString()) ?: listOf()
        }catch (e: HttpException){
            listOf()
        }
    }

    override suspend fun deleteRoom(code: String): Boolean = withContext(ioDispatcher){
        try {
            val userId = preferenceManager.getLoggedInUserId()!!
            val idToken = userDao.getIdToken(userId)
            val response = allRoomApi.deleteRoom(idToken, AnyRoomRequest(code))
            if (response.status == "success"){
                true
            }
            else{
                Log.d("Impl", response.error.toString())
                false
            }
        }catch (e: HttpException){
            false
        }
    }

    override suspend fun leaveRoom(code: String): Boolean = withContext(ioDispatcher){
        try{
            val userId = preferenceManager.getLoggedInUserId()!!
            val idToken = userDao.getIdToken(userId)
            val response = allRoomApi.leaveRoom(idToken, AnyRoomRequest(code))
            if (response.status == "success"){
                true
            }
            else{
                Log.d("Impl", response.error.toString())
                false
            }
        }catch (e: HttpException){
            false
        }
    }

    override suspend fun createRoom(description: String): RoomModel? = withContext(ioDispatcher){
        try{
            val userId = preferenceManager.getLoggedInUserId()!!
            val idToken = userDao.getIdToken(userId)

            val response = allRoomApi.createRoom(idToken, CreateRoomRequest(description))
            response.data.room.toRoomModel(userId)
        }catch (e: HttpException){
            null
        }
    }

    override suspend fun joinRoom(code: String): RoomModel? = withContext(ioDispatcher){
        try{
            val userId = preferenceManager.getLoggedInUserId()!!
            val idToken = userDao.getIdToken(userId)
            val response = allRoomApi.joinRoom(idToken, AnyRoomRequest(code))
            response.data.room.toRoomModel(userId)
        }catch (e: HttpException){
            null
        }
    }

}