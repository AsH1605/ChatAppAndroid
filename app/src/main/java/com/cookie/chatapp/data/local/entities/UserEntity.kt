package com.cookie.chatapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity (
    @PrimaryKey
    val userId: String,
    val username: String,
    val idToken: String
)