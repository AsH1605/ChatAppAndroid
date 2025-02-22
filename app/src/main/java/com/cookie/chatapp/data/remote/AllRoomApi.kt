package com.cookie.chatapp.data.remote

import com.cookie.chatapp.data.remote.dto.allRooms.CreateRoomRequest
import com.cookie.chatapp.data.remote.dto.allRooms.DeleteRoomResponse
import com.cookie.chatapp.data.remote.dto.allRooms.GetAllRoomResponse
import com.cookie.chatapp.data.remote.dto.allRooms.AnyRoomRequest
import com.cookie.chatapp.data.remote.dto.allRooms.CreateRoomResponse
import com.cookie.chatapp.data.remote.dto.allRooms.JoinRoomResponse
import com.cookie.chatapp.data.remote.dto.allRooms.LeaveRoomResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AllRoomApi {

    @POST("rooms/new")
    suspend fun createRoom(@Header("authorization") idToken: String, @Body createRoomRequest: CreateRoomRequest): CreateRoomResponse

    @POST("rooms/join")
    suspend fun joinRoom(@Header("authorization") idToken: String, @Body anyRoomRequest: AnyRoomRequest): JoinRoomResponse

    @POST("rooms/delete")
    suspend fun deleteRoom(@Header("authorization") idToken: String, @Body anyRoomRequest: AnyRoomRequest): DeleteRoomResponse

    @POST("rooms/leave")
    suspend fun leaveRoom(@Header("authorization") idToken: String, @Body anyRoomRequest: AnyRoomRequest): LeaveRoomResponse

    @GET("rooms")
    suspend fun getAllRoom(@Header("authorization") idToken: String): GetAllRoomResponse
}