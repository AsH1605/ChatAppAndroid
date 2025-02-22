package com.cookie.chatapp.data.repository

import android.util.Log
import com.cookie.chatapp.data.local.dao.UserDao
import com.cookie.chatapp.data.mapper.toRoom
import com.cookie.chatapp.data.mapper.toRoomModel
import com.cookie.chatapp.data.remote.AllRoomApi
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

    override suspend fun deleteRoom(code: Int): Boolean = withContext(ioDispatcher){
        try {
            val userId = preferenceManager.getLoggedInUserId()!!
            val idToken = userDao.getIdToken(userId)
            val response = allRoomApi.deleteRoom(idToken, code.toString())
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

    override suspend fun leaveRoom(code: Int): Boolean = withContext(ioDispatcher){
        try{
            val userId = preferenceManager.getLoggedInUserId()!!
            val idToken = userDao.getIdToken(userId)
            val response = allRoomApi.leaveRoom(idToken, code.toString())
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
            val response = allRoomApi.createRoom(idToken, description)
            response.toRoomModel(userId)
        }catch (e: HttpException){
            null
        }
    }

    override suspend fun joinRoom(code: Int): RoomModel? = withContext(ioDispatcher){
        try{
            val userId = preferenceManager.getLoggedInUserId()!!
            val idToken = userDao.getIdToken(userId)
            val response = allRoomApi.joinRoom(idToken, code.toString())
            response.data.toRoomModel(userId)
        }catch (e: HttpException){
            null
        }
    }

}