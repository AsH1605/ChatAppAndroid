package com.cookie.chatapp.data.remote

import com.cookie.chatapp.data.remote.dto.room.GetAllMessagesReq
import com.cookie.chatapp.data.remote.dto.room.GetAllMessagesRes
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface RoomApi {

    @POST("messages")
    suspend fun getMessages(@Header("authorization") idToken: String, @Body req: GetAllMessagesReq): GetAllMessagesRes
}