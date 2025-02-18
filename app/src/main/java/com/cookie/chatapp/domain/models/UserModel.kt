package com.cookie.chatapp.domain.models

data class UserModel(
    val username: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
)
