package com.cookie.chatapp.presentation.user.model

sealed interface UiEvent {
    data class OnUsernameChange(val updatedUsername: String): UiEvent
    data class OnFirstNameChange(val updatedFirstName: String): UiEvent
    data class OnLastNameChange(val updatedLastName: String): UiEvent
    data class OnEmailChange(val updatedEmail: String): UiEvent
    data class OnPasswordChange(val updatedPassword: String): UiEvent
    data object OnRegisterClicked: UiEvent
    data object OnDismissError : UiEvent
}