package com.cookie.chatapp.data.remote

import com.cookie.chatapp.data.remote.dto.user.UserRegisterRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {

    @POST("register")
    suspend fun registerUser(@Body userRegisterRequest: UserRegisterRequest): Boolean
}