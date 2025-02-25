package com.cookie.chatapp.data.remote.dto.room

import kotlinx.serialization.Serializable

@Serializable
data class JoinRoomRequest(
    val userRoom: UserRoom,
    val isFirst: Boolean
)
