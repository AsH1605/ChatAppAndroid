package com.cookie.chatapp.presentation.login.model

sealed interface UiEvent {
    data class OnUsernameChange(val updatedUsername: String): UiEvent
    data class OnPasswordChange(val updatedPassword: String): UiEvent
    data object OnRegisterClicked: UiEvent
    data object OnLoginClicked: UiEvent
    data object OnDismissError: UiEvent
}