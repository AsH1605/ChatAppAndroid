package com.cookie.chatapp.data.remote

import com.cookie.chatapp.data.remote.dto.room.GetMessageResponse
import retrofit2.http.POST

interface RoomApi {

    @POST("messages")
    suspend fun getMessages(): GetMessageResponse
}