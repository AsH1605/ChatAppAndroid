package com.cookie.chatapp.presentation.allRoom.model

sealed interface UiEvent {
    data object OnAddClicked: UiEvent
    data object OnProfileClicked: UiEvent
    data class OnRoomClicked(val code: String): UiEvent
    data object OnLogoutClicked: UiEvent
}