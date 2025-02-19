package com.cookie.chatapp.data.remote

import com.cookie.chatapp.data.remote.dto.user.UserLoginRequest
import com.cookie.chatapp.data.remote.dto.user.UserLoginResponse
import com.cookie.chatapp.data.remote.dto.user.UserRegisterRequest
import com.cookie.chatapp.data.remote.dto.user.UserRegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {

    @POST("register")
    suspend fun registerUser(@Body userRegisterRequest: UserRegisterRequest): UserRegisterResponse

    @POST("login")
    suspend fun loginUser(@Body userLoginRequest: UserLoginRequest): UserLoginResponse
}