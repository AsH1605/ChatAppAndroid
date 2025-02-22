package com.cookie.chatapp.presentation.allRoom.model

import com.cookie.chatapp.domain.models.RoomModel

data class UiState(
    val username: String,
    val room: List<RoomModel>,
    val isContextMenuVisible: Boolean
)
