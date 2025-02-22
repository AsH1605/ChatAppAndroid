package com.cookie.chatapp.data.remote

import com.cookie.chatapp.data.remote.dto.room.DeleteRoomResponse
import com.cookie.chatapp.data.remote.dto.room.GetAllRoomResponse
import com.cookie.chatapp.data.remote.dto.room.GetRoomResponse
import com.cookie.chatapp.data.remote.dto.room.JoinRoomResponse
import com.cookie.chatapp.data.remote.dto.room.LeaveRoomResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AllRoomApi {

    @POST("rooms/new")
    suspend fun createRoom(@Header("authorization") idToken: String, description: String): GetRoomResponse

    @POST("rooms/join")
    suspend fun joinRoom(@Header("authorization") idToken: String, code: String): JoinRoomResponse

    @POST("rooms/delete")
    suspend fun deleteRoom(@Header("authorization") idToken: String, code: String): DeleteRoomResponse

    @POST("rooms/leave")
    suspend fun leaveRoom(@Header("authorization") idToken: String, code: String): LeaveRoomResponse

    @GET("rooms")
    suspend fun getAllRoom(@Header("authorization") idToken: String): GetAllRoomResponse
}