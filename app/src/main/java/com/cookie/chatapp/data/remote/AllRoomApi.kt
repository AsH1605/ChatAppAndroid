package com.cookie.chatapp.data.remote

import com.cookie.chatapp.data.remote.dto.room.DeleteRoomResponse
import com.cookie.chatapp.data.remote.dto.room.GetAllRoomResponse
import com.cookie.chatapp.data.remote.dto.room.GetRoomResponse
import com.cookie.chatapp.data.remote.dto.room.JoinRoomResponse
import com.cookie.chatapp.data.remote.dto.room.LeaveRoomResponse
import retrofit2.http.Header
import retrofit2.http.POST

interface AllRoomApi {

    @POST("rooms/new")
    suspend fun createRoom(@Header("idToken") idToken: String, description: String): GetRoomResponse

    @POST("rooms/join")
    suspend fun joinRoom(@Header("idToken") idToken: String, code: String): JoinRoomResponse

    @POST("rooms/delete")
    suspend fun deleteRoom(@Header("idToken") idToken: String, code: String): DeleteRoomResponse

    @POST("rooms/leave")
    suspend fun leaveRoom(@Header("idToken") idToken: String, code: String): LeaveRoomResponse

    @POST("rooms")
    suspend fun getAllRoom(@Header("idToken") idToken: String): GetAllRoomResponse
}