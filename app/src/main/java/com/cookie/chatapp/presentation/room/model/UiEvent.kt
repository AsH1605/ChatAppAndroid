package com.cookie.chatapp.presentation.room.model

sealed interface UiEvent {
    data object OnMessageSendClicked: UiEvent
    data class OnMessageTyped(val message: String): UiEvent
    data object OnBackClicked: UiEvent
}