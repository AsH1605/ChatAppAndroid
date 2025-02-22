package com.cookie.chatapp.presentation.allRoom.model

import com.cookie.chatapp.domain.models.RoomModel

data class UiState(
    val username: String,
    val room: List<RoomModel>,
    val isContextMenuVisible: Boolean,
    val isJoinRoomDialogVisible: Boolean = false,
    val isCreateRoomDialogVisible: Boolean = false,
    val roomCode: String = "",
    val description: String = "",
    val isExpanded: Boolean = false
)
