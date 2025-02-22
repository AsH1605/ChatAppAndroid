package com.cookie.chatapp.presentation.allRoom.model

sealed interface UiEvent {
    data object OnFabClicked: UiEvent
    data object OnProfileClicked: UiEvent
    data class OnRoomClicked(val code: String): UiEvent
    data object OnLogoutClicked: UiEvent
    data object OnCreateRoomClicked: UiEvent
    data object OnJoinRoomClicked: UiEvent
    data object OnCreateDialogButtonClicked: UiEvent
    data object OnJoinDialogButtonClicked: UiEvent
    data class OnDescriptionUpdate(val description: String): UiEvent
    data class OnRoomCodeUpdate(val roomCode: String): UiEvent
}